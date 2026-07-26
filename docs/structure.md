# Batakjava Module Structure

This document describes the `batakjava/` module — the language extension layer that adds **versioned types** on top of ExtendJ (a Java compiler framework built with JastAdd reference-attribute grammars). All files use the `.jrag` (JastAdd attribute grammar) format except for the syntax files and `PrettyPrinter.jadd`.

---

## Overview

Batakjava extends Java with a **version polymorphism** feature. A class or interface can be declared at a specific version (e.g., `class Foo ver 2 { ... }`), and call sites can reference a specific version or parameterize over version variables. The compiler:

1. **Parses** the extended syntax.
2. **Resolves** versioned types and builds a **dependency graph** grouping mutually-dependent types into strongly-connected components (SCCs).
3. **Generates constraints** (CNF-like Boolean formulas) per component that capture which type version assignments are valid.
4. **Solves** the constraints using the **Z3 SMT solver** to find concrete version assignments.
5. **Checks** applicability of method calls, constructors, and assignments post-solving.
6. **Compiles** each versioned type into a pair of plain Java interface + implementing class (the "version erasure").

---

## Directory Layout

```
batakjava/
├── syntax/
│   ├── Batakjava.ast
│   ├── Batakjava.flex
│   └── Batakjava.parser
├── frontend/
│   ├── Version.jrag
│   ├── TypeAnalysis.jrag
│   ├── DependencyGraph.jrag
│   ├── LookupType.jrag
│   ├── LookupMethod.jrag
│   ├── LookupConstructor.jrag
│   ├── LookupVariable.jrag
│   ├── QualifiedNames.jrag
│   ├── ResolveAmbiguousNames.jrag
│   ├── InnerClasses.jrag
│   ├── ClassPath.jrag
│   ├── FrontendMain.jrag
│   └── PrettyPrinter.jadd
├── constraint/
│   ├── Definition.jrag
│   ├── Generation.jrag
│   ├── Solving.jrag
│   └── Checking.jrag
└── backend/
    ├── Compilation.jrag
    └── Overlap.jrag
```

---

## `syntax/` — Grammar

### `Batakjava.ast`
Defines the new AST node types that extend the standard Java grammar:

| Node | Description |
|------|-------------|
| `VersionClassDecl : ClassDecl` | A class declaration annotated with a version number (`ver N`). |
| `GenericVersionClassDecl : VersionClassDecl` | A versioned class additionally parameterized over version variables (`«V»`). |
| `VersionInterfaceDecl : InterfaceDecl` | A versioned interface declaration. |
| `GenericVersionMethodDecl : MethodDecl` | A method parameterized over version variables. |
| `ParVersionMethodAccess : MethodAccess` | A method call that supplies explicit version arguments. |
| `VersionTypeAccess : Access` | A type reference pinned to a single version (`Foo#2#`). |
| `ParVersionTypeAccess : Access` | A type reference parameterized with version arguments (`Foo«V»`). |
| `VersionVariable` | A version variable with optional range constraints (`V`, `V < 3`, `1 <= V < 3`). |
| `VersionRange` (abstract) + `VersionLT/LE/GT/GE` | Range bound directions for version variables. |
| `VersionArgument` (abstract) + `VersionVarArgument / VersionNumArgument` | A version argument is either a named variable or a literal number. |
| `Version` | Wraps a version number string. |

### `Batakjava.flex`
Adds four new scanner tokens to the standard Java lexer:

| Token | Lexeme |
|-------|--------|
| `VER` | `ver` keyword |
| `SHARP` | `#` (version pin operator) |
| `LGUI` / `RGUI` | `«` / `»` (version variable bracket characters) |

### `Batakjava.parser`
Extends the standard Java parser with grammar rules for the new syntax:

- **`class_declaration`** — matches `class Foo ver N { }` → `VersionClassDecl`, and `class Foo ver N «V» { }` → `GenericVersionClassDecl`.
- **`interface_declaration`** — matches `interface Foo ver N { }` → `VersionInterfaceDecl`.
- **`method_header`** — matches `«V» ReturnType name(...)` → `GenericVersionMethodDecl`.
- **`method_invocation`** — matches `obj.«V»method(args)` → `ParVersionMethodAccess`.
- **`name`** — extended to match `Foo«V»` → `ParVersionTypeAccess` and `Foo#N#` → `VersionTypeAccess`.
- **`version_variables` / `version_variable` / `version_range`** — parse version variable declarations with optional range constraints.
- **`version_arguments` / `version_argument`** — parse version argument lists (numeric literals or identifiers).
- **`version_number`** — parses a `NUMERIC_LITERAL` into a `Version` node.

---

## `frontend/` — Semantic Analysis

### `Version.jrag` — Version Metadata & Maps
- Synthesizes `version()` on `TypeDecl`, returning `"-1"` for unversioned types and the version string for `VersionClassDecl` / `VersionInterfaceDecl`.
- Defines `verMap(TypeDecl)` on expressions and variables — builds a `Map<String, VersionArgument>` that maps each version variable name to the argument supplied at a use site. Used during method and constructor applicability checks to resolve parameterized calls.
- Defines `verMap(ParVersionTypeAccess)` on `GenericVersionClassDecl` and `GenericVersionMethodDecl` — maps each version variable position to the supplied argument.
- Synthesizes `isVersionNumArgument()`, `isVersionVarArgument()`, `hasVersionNumArgument()` helpers on `VersionArgument` nodes.
- Provides `map(verMap)` on `Access` nodes — resolves a version variable reference to a concrete `TypeDecl` given a variable map.

### `TypeAnalysis.jrag` — Type Numbering & Type Sets
Contains two aspects:

**`TypeMap`** — gives every `TypeDecl` a unique integer ID:
- `Program.typeCount` / `Program.typeMap` — global counter and `Integer → TypeDecl` registry.
- `TypeDecl.typeNum()` — lazily assigns and registers a unique integer to each type. Used as a Z3 integer constant value.
- `Program.printTypeMap()` — debug helper to dump the full map.

**`TypeSet`** — synthesizes a set of possible `TypeDecl` candidates for every expression node:
- `TypeDecl.isVersioned()` — `true` for `VersionClassDecl` and `VersionInterfaceDecl`.
- `Expr.typeSet()` — returns the singleton `{type()}` for most expressions.
- Overridden for `TypeAccess`, `VersionTypeAccess`, `ParVersionTypeAccess`, `ClassInstanceExpr`, `VarAccess`, `MethodAccess`, `Dot`, `ArrayAccess`, `SuperAccess`, `CastExpr`, `ParExpr`, variables, and declarators — each navigating through the version layer to return all applicable `TypeDecl` versions.

**`TypeScopePropagation`** (in `LookupType.jrag`) — threads `returnTypeAccess()` through the AST so `ReturnStmt` can find the declared return type access of its enclosing method.

### `DependencyGraph.jrag` — SCC-Based Component Grouping
Implements the core **dependency analysis** that drives per-component constraint solving.

**`Graph` inner class:**
- Stores an adjacency matrix (`List<List<Boolean>>`).
- `setComponents()` runs **Tarjan's strongly-connected components** algorithm (the private `Components` inner class), identifying sets of mutually-dependent versioned types.
- `joinComponents()` builds a condensation graph (DAG of components) for topological ordering.

**Program-level API:**
- `Program.initializeGraph()` — triggers type numbering, builds the `Graph`, computes SCCs, and constructs the condensation.
- `Program.addEdge(src, dst)` — adds a dependency edge between two `TypeDecl`s.
- `setGraphEdges()` (collection attribute) — populated by `TypeAccess` nodes: for each versioned type referenced, an edge is added from the host type to that type.
- `Program.getComponent(i)` — returns the `Set<TypeDecl>` for SCC `i`.
- `TypeDecl.numComponent()` / `Expr.numComponent()` — returns which component index contains a given type or expression.

### `LookupMethod.jrag` — Version-Aware Method Lookup
- `Expr.verLookupMethod(name)` — inherited attribute returning a `Map<TypeDecl, Collection<MethodDecl>>` (qualifier type → matching methods). Overridden at `Dot` to map across all types in the qualifier's `typeSet()`.
- `MethodAccess.verApplicableAndAccessible(m, t)` — checks method applicability considering version argument counts and subtype constraints; returns an `Optional<Formula>` (empty if inapplicable, otherwise a formula encoding the constraints).
- `ParVersionMethodAccess.verApplicableAndAccessible` — additionally validates that the number of version arguments matches the method's version variable count and that numeric arguments satisfy range constraints.
- `TypeDecl.eagerLocalMethods()` — fast local method list, used in member method lookups.

### `LookupConstructor.jrag` — Version-Aware Constructor Lookup
- `ClassInstanceExpr.verApplicableAndAccessible(ConstructorDecl)` — checks constructor applicability with subtype formulas.
- `ClassInstanceExpr.verApplicableAndAccessible(ConstructorDecl, verMap)` — variant that also resolves version-variable-typed parameters against a concrete `verMap`, used for generic versioned class instantiation.
- `ConstructorAccess.verApplicableAndAccessible(ConstructorDecl)` — same for `super(...)` calls.

### `LookupVariable.jrag` — Version-Aware Field Lookup & Version Variable Binding
- `TypeDecl.verMemberFields(name)` — looks up fields, respecting versioned superclass chains.
- `Expr.verLookupVariable(name)` — inherited attribute; overridden at `Dot` to map across the qualifier's `typeSet()`.
- **`boundSet` infrastructure** — resolves what concrete type a version variable is "bound" to by scanning the body of a `GenericVersionClassDecl` or `GenericVersionMethodDecl`. Traverses fields, constructors, method signatures, and method bodies to find all `TypeAccess` nodes that use a given version variable.
- `VersionVariable.typeAccess()` — synthesizes the unique `TypeAccess` associated with a version variable from its `boundSet`.

### `QualifiedNames.jrag` — Version-Qualified Names
- `TypeDecl.verFullName()` — returns the fully qualified name suffixed with ` v.<version>` for versioned types (e.g., `com.example.Foo v.2`). Used in error messages and debug output.

### `ResolveAmbiguousNames.jrag` — Access Type Predicates
- `Access.isVersionTypeAccess()` / `isParVersionTypeAccess()` — boolean attributes distinguishing version access nodes from plain `TypeAccess`; used in disambiguation logic.

### `InnerClasses.jrag` — Method Ownership
- `TypeDecl.owns(BodyDecl)` / `BodyDecl.owned(TypeDecl)` — determines whether a method body declaration locally belongs to a type (by signature). Used during code generation to avoid adding inherited methods as duplicates.

### `ClassPath.jrag` — Compilation Unit Registration
- `Program.addNewCompilationUnit(CompilationUnit)` — adds a generated compilation unit to the program's class path and compilation unit list, thread-safely. Used by the backend when emitting generated interfaces and classes.

### `FrontendMain.jrag` — Pipeline Orchestration
Defines the abstract class `BatakjavaFrontend` (extends ExtendJ's `Frontend`) which drives the full compilation pipeline:

1. Initialize options and parse arguments.
2. Add source files to `program`.
3. Verify `java.lang.Object` is available.
4. Iterate over all compilation units; call `processBatakCompilationUnit` (parse-error-only check — semantic errors are deferred to constraint solving).
5. Call `program.solve()` to run Z3-based constraint solving across all components.
6. Return success or a specific error code.

### `PrettyPrinter.jadd` — Source Reconstruction
Implements `prettyPrint(PrettyPrinter out)` for every new AST node type, producing valid Batakjava source from the AST:

- `Version` → prints the version ID string.
- `VersionClassDecl` → `class Foo ver N { ... }` with optional `extends`/`implements`.
- `GenericVersionClassDecl` → `class Foo ver N«V» { ... }`.
- `VersionInterfaceDecl` → `interface Foo ver N { ... }`.
- `GenericVersionMethodDecl` → `«V» RetType name(params)`.
- `VersionTypeAccess` → `Type#N#`.
- `ParVersionTypeAccess` → `Type«V»`.
- `ParVersionMethodAccess` → `obj.«V»method(args)`.
- `VersionVariable` and `VersionRange` nodes → range strings like `1 <= V < 3`.
- `VersionArgument` nodes → numeric or identifier strings.

---

## `constraint/` — Constraint System

### `Definition.jrag` — Data Structures
Defines the constraint representation used as input to Z3, using Z3's Java API:

**`Formula`** — a disjunction of `Clause`s:
- `add(Formula/Clause)` — disjunctive union.
- `multiply(Formula/Clause)` — Cartesian product (distributive AND-of-ORs expansion, giving a CNF-like structure).
- `convert(Context) → BoolExpr` — converts to a Z3 `mkOr(...)` expression.

**`Clause`** — a conjunction of `Lit`s:
- `add(Clause/Lit)` — conjunctive merge.
- `convert(Context) → BoolExpr` — converts to a Z3 `mkAnd(...)` expression.

**`Lit`** (interface) — a single Boolean literal with two implementations:
- `TypeLit(IntExpr varConst, Integer typeInt)` — asserts `varConst == typeInt` (assigns a specific type version to a solver variable).
- `VarLit(IntExpr constA, IntExpr constB)` — asserts `constA == constB` (two expression slots must resolve to the same type).

### `Generation.jrag` — Constraint Generation
Synthesizes constraints from the AST expressions:

**`ConstraintVariable` aspect:**
- `Program.varCount` / `Program.varMap` — global counter assigning an integer "slot" to every `ASTNode` that needs a type variable.
- `Expr.varNum()` — assigns a slot; overridden for `Dot`, `VarAccess`, `CastExpr`, `ParExpr`, etc., to share slots with their sub-expressions.
- `Variable.varNum()` — parameters/declarators delegate to their type access.
- `Expr.solverConst` (mutable field) — holds the Z3 `IntExpr` constant initialized during `initConst()`.
- `initConst()` (collection attribute) — traverses all `Access` nodes and calls `ctx.mkIntConst(var())` to create Z3 integer constants.
- `TypeDecl.getTerms()` (collection attribute) — gathers all solver constants from `Access` nodes within a type declaration; used by the solver to enumerate variables to block/fix during solution enumeration.

**`ConstraintGeneration` aspect:**
- `TypeDecl.constraint()` (collection attribute) — aggregates `Formula`s contributed by all `Expr` nodes in the type.
- `Expr.constraint()` — default: empty formula. Overridden for:
  - `ThisAccess` → single `TypeLit` fixing it to the host type.
  - `SuperConstructorAccess` → formulas encoding all applicable super-constructor calls.
  - `SuperAccess` → disjunction over all possible supertypes.
  - `TypeAccess`, `VersionTypeAccess`, `ParVersionTypeAccess` → disjunction over `typeSet()`.
  - `ClassInstanceExpr` → formulas from all applicable constructors.
  - `MethodAccess` → formulas from all applicable versioned methods.
  - `Dot` → delegates to last access.
  - `VarAccess` → delegates to the variable's declaration.
  - Arithmetic/comparison operators → propagate constraints through operands.
  - `CastExpr`, `ParExpr`, `ArrayAccess`, `ConditionalExpr`, `AssignExpr` → appropriate delegation.
- `subtype(Access)` / `subtype(TypeDecl)` on `Expr` — returns an `Optional<Formula>` encoding a subtype constraint between two type slots.

### `Solving.jrag` — Z3 Integration
Drives per-component constraint solving:

- `Program.contexts` — one Z3 `Context` per SCC component.
- `Program.componentModels` / `Program.models` — lists of Z3 `Model`s (solutions) per component.
- `Expr.context()` / `TypeDecl.context()` — delegates to `contexts.get(numComponent())`.
- `CompilationUnit.model` — the chosen `Model` for this unit's component.
- `Expr.model()` → delegates to compilation unit's model.
- `Expr.getSolution()` — evaluates `solverConst` against the chosen model to return the concrete `TypeDecl`.
- `Program.getAllSolutions(Solver, i)` — enumerates **all** satisfying assignments for component `i` using a block-and-fix strategy (blocks each previously found assignment, fixing earlier variables to isolate changes).
- `Program.solve(i)` — for component `i`: runs `Checking` errors first, then adds all constraint formulas to the solver, calls `getAllSolutions`, stores the first model for the units in the component.
- `Program.solve()` — top-level entry point: calls `initializeGraph()`, creates Z3 contexts, calls `initConst()`, then calls `solve(i)` for each component in order.

### `Checking.jrag` — Applicability Checking
Post-solve (or at solve time) semantic checks, implemented as **collection attributes** contributed to `TypeDecl.check()`:

- `ParVersionTypeAccess.check()` — verifies that for every numeric version argument, at least one model satisfies the version ranges.
- `ClassInstanceExpr.check()` — verifies at least one type in `typeSet()` has an applicable constructor.
- `SuperConstructorAccess.check()` — verifies that the super class has an applicable constructor.
- `Declarator.check()` — checks that the initializer's type is a subtype of the declared type (version-aware).
- `MethodAccess.check()` — verifies that `verLookupMethod` returns results and at least one method is applicable.
- `ParVersionTypeAccess.applicable(TypeDecl t, Model m)` — helper that checks whether the version arguments in a parameterized access match a specific model for type `t`.

---

## `backend/` — Code Generation

### `Compilation.jrag` — Version Erasure
Implements the `Program.compile()` method that transforms the versioned AST into plain Java:

**`Program.compile()`** — iterates over all SCCs and for each versioned type in each component:
1. **Interface generation** (`compileInterface`) — creates a plain `interface Foo { ... }` containing all method signatures (fields become getter methods, constructors become factory signatures).
2. **Interface factory** (`compileInterfaceFactory`) — creates a companion `interface Foo_Factory { ... }` with constructor signatures.
3. **Class generation** (`compileClass`) — creates the concrete `class Foo_v2 implements Foo { ... }`.
4. **Class factory** (`compileClassFactory`) — creates `class Foo_v2_Factory implements Foo_Factory { ... }`.

After all types are processed:
- `removeDuplicate(name)` — deduplicates method signatures in the generated interface (multiple version contributions may produce the same signature).
- `processMethod(name)` — for each concrete class, adds any interface methods that the class doesn't locally define by generating delegation stubs.
- `processConstructor(name)` — same for factory methods.

Key synthesized attributes:
- `TypeDecl.compileInterface()` — NTA (non-terminal attribute) returning the generated interface type list. Default: empty; overridden for `VersionClassDecl` and `VersionInterfaceDecl`.
- `TypeDecl.compileInterfaceFactory()` — NTA for the factory interface.
- `TypeDecl.compileClass()` — NTA for the concrete class; default returns a copy of the original.
- `CompilationUnit.compile(List<TypeDecl>)` — NTA wrapping a list of types into a new `CompilationUnit` preserving package and class source.

### `Overlap.jrag` — Method Name Conflict Resolution
Handles the case where two versions of the same method have the **same signature but different return types** — which would be illegal in a single Java interface:

- `BodyDecl.source` — points back to the original source body declaration (field or method) that this generated method was derived from.
- `BodyDecl.rename` — flag set when a method must be renamed due to overlap.
- `TypeDecl.addNewMethod(BodyDecl)` / `InterfaceDecl.addNewMethod(BodyDecl)` — adds a body declaration to an interface; does nothing for non-interface types.
- `MethodDecl.addTo(InterfaceDecl)` — the core overlap logic:
  1. Checks if an existing member method has the same signature but a **different** resolved return type.
  2. If an overlap exists, renames the new method to `originalName__ReturnTypeName` (e.g., `getValue__String`) and sets `source.rename = true` on the originating declaration so the concrete class method is also renamed consistently.
  3. Otherwise, adds the method unchanged.

---

## Compilation Pipeline Summary

```
Source files (.java with ver syntax)
        │
        ▼
   [Lexer/Parser]  (Batakjava.flex + Batakjava.parser)
        │  Produces versioned AST nodes
        ▼
   [Frontend]
        ├─ TypeMap: number every TypeDecl
        ├─ DependencyGraph: build type→type edges, compute SCCs
        ├─ Lookup: resolve versioned method/field/constructor references
        └─ (parse errors reported; semantic errors deferred)
        │
        ▼
   [Constraint Generation]  (Generation.jrag)
        │  One Formula set per SCC component
        ▼
   [Constraint Solving]  (Solving.jrag + Z3)
        │  Produces a Model (type assignment) per component
        ▼
   [Applicability Checking]  (Checking.jrag)
        │  Reports errors if no valid assignment exists
        ▼
   [Backend / Code Generation]  (Compilation.jrag + Overlap.jrag)
        │  Emits plain Java: Foo interface + Foo_vN class + factories
        ▼
   [ExtendJ bytecode emission]  (generateClassfile)
        │
        ▼
   .class files
```

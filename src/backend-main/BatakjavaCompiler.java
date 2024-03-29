package org.batakjava;

import java.io.File;
import java.util.Collection;

import org.extendj.ast.CompilationUnit;
import org.extendj.ast.BatakjavaBackend;
import org.extendj.ast.Problem;
import org.extendj.ast.Program;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Compile Java programs.
 */
public class BatakjavaCompiler extends BatakjavaBackend {

  protected enum Mode {
    COMPILE,
    PRETTY_PRINT,
    DUMP_TREE,
    STRUCTURED_PRINT,
  }

  /**
   * Entry point for the compiler frontend.
   * @param args command-line arguments
   */
  public static void main(String args[]) {
    int exitCode = new BatakjavaCompiler().run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  private Mode mode = Mode.COMPILE;
  //private Mode mode = Mode.PRETTY_PRINT;

  /**
   * Initialize the compiler.
   */
  public BatakjavaCompiler() {
    this("Batakjava");
  }

  /**
   * Initialize the compiler.
   * @param toolName the name of the compiler
   */
  protected BatakjavaCompiler(String toolName) {
    super(toolName, "0.0.1");
  }

  /**
   * Run the compiler.
   * @param args command-line arguments
   * @return 0 on success, 1 on error, 2 on configuration error, 3 on system
   * error
   */
  public int run(String args[]) {
    return run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

  @Override
  protected int processCompilationUnit(CompilationUnit unit) {
    if (mode != Mode.STRUCTURED_PRINT) {
      return super.processCompilationUnit(unit);
    } else {
      if (unit != null && unit.fromSource()) {
        try {
          System.out.println(unit.structuredPrettyPrint());
        } catch (IOException e) {
          e.printStackTrace(System.err);
          return EXIT_ERROR;
        }
      }
      return EXIT_SUCCESS;
    }
  }

  @SuppressWarnings("rawtypes")
  @Override
  protected void processErrors(Collection<Problem> errors, CompilationUnit unit) {
    super.processErrors(errors, unit);
    switch (mode) {
      case PRETTY_PRINT:
        try {
          unit.prettyPrint(new PrintStream(System.out, false, "UTF-8"));
        } catch (IOException e) {
          e.printStackTrace(System.err);
        }
        return;
      case DUMP_TREE:
        System.out.println(unit.dumpTreeNoRewrite());
        return;
    }
  }

  @Override
  protected void processNoErrors(CompilationUnit unit) {
    switch (mode) {
      case COMPILE:
        unit.generateClassfile();
        return;
      case PRETTY_PRINT:
        try {
          unit.prettyPrint(new PrintStream(System.out, false, "UTF-8"));
        } catch (IOException e) {
          e.printStackTrace(System.err);
        }
        return;
      case DUMP_TREE:
        System.out.println(unit.dumpTreeNoRewrite());
        return;
    }
  }

  @Override
  protected void initOptions() {
    super.initOptions();
    program.options().addKeyOption("-XstructuredPrint");
  }

  /**
   * Check that the output directory given in args exists.
   */
  @Override
  public int processArgs(String[] args) {
    int result = super.processArgs(args);
    if (result != 0) {
      return result;
    }
    if (program.options().hasValueForOption("-d")) {
      String destDir = program.options().getValueForOption("-d");
      File dir = new File(destDir);
      if (!dir.isDirectory()) {
        System.err.println("Error: output directory not found: " + destDir);
        return EXIT_CONFIG_ERROR;
      }
    }
    if (program.options().hasOption("-XprettyPrint")) {
      mode = Mode.PRETTY_PRINT;
    } else if (program.options().hasOption("-XdumpTree")) {
      mode = Mode.DUMP_TREE;
    } else if (program.options().hasOption("-XstructuredPrint")) {
      mode = Mode.STRUCTURED_PRINT;
    }
    return EXIT_SUCCESS;
  }
}

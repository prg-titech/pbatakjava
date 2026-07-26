# Version Polymorphic BatakJava

## System requirements

- Gradle (8.10.1)
- Apache Maven (3.9.6)
- Java 8
- [Z3 Solver](https://github.com/Z3Prover/z3) version 4.8.10

### Gradle installation

- MacOS

Install using brew:

```
brew install gradle
```

### Z3 solver installation

1. Download the Z3 version 4.8.10 ([link](https://github.com/Z3Prover/z3/releases/tag/z3-4.8.10)) for your OS.

2. Unpack the downloaded file

3. Install to local Maven repository:

```

```

Reference: https://stackoverflow.com/questions/60403775/how-to-setup-a-java-development-environment-for-z3 

## Compiling pbatakjava

From the root:

```bash
gradle jar
```

## Running tests

1. Install poetry

```bash
poetry install
```

2. Run tests

```bash
poetry run python run_tests.py
```

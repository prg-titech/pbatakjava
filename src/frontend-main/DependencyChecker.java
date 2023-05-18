package org.batakjava;

import org.extendj.ast.DependencyFrontend;
import org.extendj.ast.Program;

public class DependencyChecker extends DependencyFrontend {

  public static void main(String[] args) {
    int exitCode = new DependencyChecker().run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  public DependencyChecker() { super("BatakJava Dependency Checker", "0.0.1"); }

  public int run(String args[]) {
    return run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

}

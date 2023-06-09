package org.batakjava;

import org.extendj.ast.BatakjavaFrontend;
import org.extendj.ast.Program;

public class BatakjavaChecker extends BatakjavaFrontend {

  public static void main(String[] args) {
    int exitCode = new BatakjavaChecker().run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  public BatakjavaChecker() { super("Batakjava Checker", "0.0.1"); }

  public int run(String args[]) {
    return run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }
}
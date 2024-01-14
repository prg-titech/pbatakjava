public class IfStmt ver 2 «V,W,X» implements Stmt#2# {
  public Exp#V# ifCond;
  public Stmt#W# thenStmt;
  public Stmt#X# elseStmt;
  public IfStmt(Exp#V# ifCond, Stmt#W# thenStmt, Stmt#X# elseStmt) {
    this.ifCond = ifCond;
    this.thenStmt = thenStmt;
    this.elseStmt = elseStmt;
  }
  public void interp(Env env) {
    if (this.ifCond.eval(env) == 1) {
      this.thenStmt.interp(env);
    } else {
      this.elseStmt.interp(env);
    }
  }
}

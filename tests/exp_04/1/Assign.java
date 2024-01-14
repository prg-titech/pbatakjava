public class Assign ver 1 «V» implements Stmt#1# {
  public Var dest;
  public Exp#V# source;
  public Assign(Var dest, Exp#V# source) {
    this.dest = dest;
    this.source = source;
  }
  public void interp(Env env) {
    env.set(this.dest.get(), this.source.eval(env));
  }
}

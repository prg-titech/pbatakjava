public class Var ver 2 implements Exp#2# {
  public String var;
  public Var(String var) {
    this.var = var;
  }
  public String get() { return this.var; }
  public Integer eval(Env env) {
    if (!env.has(this.var)) {
      env.set(this.var, new Integer(0));
    }
    return env.get(this.var);
  }
}

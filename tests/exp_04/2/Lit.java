public class Lit ver 2 implements Exp#2# {
  public Integer value;
  public Lit(int v) { this.value = v; }
  public Integer eval(Env env) { return this.value; }
}

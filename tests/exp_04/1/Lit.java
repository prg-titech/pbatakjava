public class Lit ver 1 implements Exp#1# {
  public Integer value;
  public Lit(int v) { this.value = v; }
  public Integer eval(Env env) { return this.value; }
}

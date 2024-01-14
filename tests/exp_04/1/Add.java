public class Add ver 1 «V,W» implements Exp#1# {
  public Exp#V# left;
  public Exp#W# right;
  public Add(Exp#V# l, Exp#W# r) {
    this.left = l; this.right = r;
  }
  public Integer eval(Env env) {
    return this.left.eval(env) + this.right.eval(env);
  }
}

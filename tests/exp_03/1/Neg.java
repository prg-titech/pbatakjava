public class Neg ver 1 «V» implements Exp#1# {
  public Exp#V# exp;
  public Add(Exp#V# exp) {
    this.exp = exp;
  }
  public int interp() {
    return -this.exp.interp();
  }
}

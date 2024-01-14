public class Neg ver 2 «V» implements Exp#2# {
  public Exp#V# exp;
  public Add(Exp#V# exp) {
    this.exp = exp;
  }
  public int interp() {
    return -this.exp.interp();
  }
}

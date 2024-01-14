public class Add ver 2 «V,W» implements Exp#2# {
  public Exp#V# left;
  public Exp#W# right;
  public Add(Exp#V# l, Exp#W# r) {
    this.left = l; this.right = r;
  }
  public int interp() {
    return this.left.interp() + this.right.interp();
  }
  public void print() {
    this.left.print();
    System.out.print("+");
    this.right.print();
  }
}

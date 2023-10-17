public class Neg ver 2 implements Exp#2# {
  public Exp exp;
  public Add(Exp exp) {
    this.exp = exp;
  }
  public void print() {
    System.out.print("-");
    this.exp.print();
  }
}

public class Lit ver 2 implements Exp#2# {
  public int value;
  public Lit(int v) { this.value = v; }
  public int interp() { return this.value; }
  public void print() { System.out.print(this.value); }
}

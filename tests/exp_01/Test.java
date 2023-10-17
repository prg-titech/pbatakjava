public class Test {
  public static void main(String[] args) {
    Exp#1# exp_1 = new Add«1,1»(new Lit(1), new Lit(2));
    Exp#1# exp_2 = new Add«1,2»(new Lit(1), new Neg(new Lit(1)));
    exp_1.print();
    System.out.println();
    exp_2.print();
  }
}

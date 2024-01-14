public class Test {
  public static void main(String[] args) {
    // A component with only version 1
    Exp#1# exp_1 = new Add«1,1»(new Lit(1), new Lit(1));

    // A component with only version 2
    Exp#2# exp_2 = new Add«2,2»(new Lit(2), new Lit(2));

    // A version 2 expression with mixed components
    Exp#2# exp_3 = new Add«1,2»(exp_1, exp_2);     // rejected because exp_1 can't call print()

  }
}

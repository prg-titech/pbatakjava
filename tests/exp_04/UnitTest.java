public class UnitTest {
  public static void test() {
    // we can test by versions, but what's the difference from simply separating namespace?

    // mixing? what kind of simple example we can come up with?
       // eventually we need some kind of diamond dependency?

    // version 1 statement
    Stmt#1# prog_1 = new For«1»(
      new Integer(1),
      new Integer(10),
      new Assign«1»(
        new Var("x"),
        new Add«1,1»(
          new Var("x"),
          new Lit(2)
        )
      )
    );
    Env env_1 = new Env();
    prog_1.interp(env_1);
    System.out.println(env_1.get("x"));
    //assert env_1.get("x") == 20;

    // version 2 statement
    Stmt#2# prog_2 = new IfStmt«2,2,2»(
      new Var("x"),
      new Assign«2»(
        new Var("x"),
        new Lit(2)
      ),
      new Assign«2»(
        new Var("x"),
        new Lit(-2)
      )
    );
    Env env_2 = new Env();
    prog_2.interp(env_2);
    System.out.println(env_2.get("x"));

    Stmt#2# prog_3 = new For«2»(
      new Integer(1),
      new Integer(10),
      new Assign«1»(
        new Var("x"),
        new Add«2,2»(
          new Var("x"),
          new Lit(2)
        )
      )
    );
  }
  public static void main(String[] args) {
    new UnitTest().test();
  }
}

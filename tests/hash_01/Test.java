public class Test {
  public static void main(String[] args) {
    String s = args[0];
    Hash hash = new Hash();
    HashValue digest = hash.mkHash(s);
    Dir dir = new Dir();
    if (dir.exists(digest)) {
      System.out.println("found");
    } else {
      System.err.println("not found");
    }
  }
}
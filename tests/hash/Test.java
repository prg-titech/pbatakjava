public class Test ver 1{
  public static void main(String[] args) {
    String s = "a";
    Hash hash = new Hash();
    int digest = hash.mkHash(s);
    Dir dir = new Dir();
    if (dir.exists(digest)) {
      System.out.println("found");
    } else {
      System.err.println("not found");
    }
  }
}

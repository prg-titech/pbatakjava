public class Test {
  public static void main(String[] args) {
    String s = args[0];

    Hash#1# hash1 = new Hash#1#();
    HashValue#1# digest1 = hash1.mkHash(s);

    Hash#2# hash2 = new Hash#2#();
    HashValue#2# digest2 = hash2.mkHash(s);

    Dir«1,1» dir = new Dir«1,1»();
    boolean b1 = dir.exists(digest1);
    boolean b2 = dir.exists(digest2); // this has to rejected

    Dir«1,2» dirWrong = new Dir«1,2»();
  }
}
public class Test {
  public static void main(String[] args) {
    String s = args[0];

    Hash#1# hash1 = new Hash#1#();
    HashValue#1# digest1 = hash1.mkHash(s);

    Hash#2# hash2 = new Hash#2#();
    HashValue#2# digest2 = hash2.mkHash(s);

    Dir«1,1» dir1 = new Dir«1,1»();
    Dir«2,2» dir2 = new Dir«2,2»();

    System.out.println(dir1.exists(digest1));
    System.out.println(dir2.exists(digest2));
  }
}
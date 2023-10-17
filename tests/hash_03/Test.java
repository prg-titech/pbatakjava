public class Test ver 1 {
  public «V,W» boolean check(HashValue#V# d) {
    Dir«V,W» dir = new Dir«V,W»();
    return dir.exists(d);
  }
  public static void main(String[] args) {
    String s = args[0];

    Hash#1# hash1 = new Hash#1#();
    HashValue#1# digest1 = hash1.mkHash(s);

    Hash#2# hash2 = new Hash#2#();
    HashValue#2# digest2 = hash2.mkHash(s);

    /*
    Dir«1,1» dir = new Dir«1,1»();
    boolean b1 = dir.exists(digest1);
    //boolean b2 = dir.exists(digest2); // this has to rejected

    Dir«2,2» dir2 = new Dir«2,2»();
    boolean b2 = dir2.exists(digest2);
    */

    new Test().«2,2»check(digest1);
  }
}
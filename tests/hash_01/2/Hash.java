public class Hash ver 2 {
  public HashValue mkHash(String s) {
    if (s.equals("a")) {
      return new HashValue(-1);
    }
    return new HashValue(0);
  }
  public boolean match(String s, HashValue h) {
    return this.mkHash(s).equals(h);
  }
}

/**
 * Using type parameters, work on this!
 * 
 * interface HashAlgorithm { }
 * 
 * interface CRC32 extends HashAlgorithm { }
 * 
 * interface SHA extends HashAlgorithm { }
 * 
 * public class HashValue ver 2 <T implements HashAlgorithm> {
 *   ...
 * }
 * 
 * public class Hash ver 2 <T extends CRC32> {
 *   public HashValue<T> mkHash(String s) {
 *     ...
 *     return new HashValue<T>(0);
 *   }
 * }
 */
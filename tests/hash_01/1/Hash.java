public class Hash ver 1 {
  public HashValue#1# mkHash(String s) {
    if (s.equals("a")) {
      return new HashValue#1#(1);
    }
    return new HashValue#1#(0);
  }
  // if int is the class HashValue, we can control the version
  // by making sure that it is the same version as this class
  public boolean match(String s, HashValue#1# h) {
    return this.mkHash(s).equals(h);
  }
}
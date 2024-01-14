public class Hash ver 2 {
  public Hash() {}
  public HashValue#2# mkHash(String s) {
    if (s.equals("a")) {
      return new HashValue#2#(-1);
    }
    return new HashValue#2#(0);
  }
  public boolean match(String s, HashValue#2# h) {
    return this.mkHash(s).equals(h);
  }
}
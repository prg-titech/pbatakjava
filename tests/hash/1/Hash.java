public class Hash ver 1 {
  public int mkHash(String s) {
    return 1;
  }
  // if int is the class HashValue, we can control the version
  // by making sure that it is the same version as this class
  public boolean match(String s, int h) {
    return mkHash(s) == h;
  }
}

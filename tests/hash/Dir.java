public class Dir ver 1 {
  public String[] getFiles() {
    return {"a"};
  }
  public boolean exists(int h) {
    String[] fs = getFiles();
    // we can also reinforce hash with a specific version
    Hash hash = new Hash();
    for (int i = 0; i < fs.length; i++) {
      if (hash.match(f[i], h)) {
        return true;
      }
    }
    return false;
  }
}

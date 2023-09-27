public class Dir ver 1 {
  public String[] getFiles() {
    String[] files = new String[1];
    files[0] = "a";
    return files;
  }
  public boolean exists(HashValue h) {
    String[] fs = getFiles();
    // we can also reinforce hash with a specific version
    Hash hash = new Hash();
    for (int i = 0; i < fs.length; i++) {
      if (hash.match(fs[i], h)) {
        return true;
      }
    }
    return false;
  }
}
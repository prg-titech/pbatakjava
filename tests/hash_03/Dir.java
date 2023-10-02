public class Dir ver 1 «V,W» {
  public Dir() {}

  public String[] getFiles() {
    String[] files = new String[1];
    files[0] = "a";
    return files;
  }
  public boolean exists(HashValue#V# h) {
    String[] fs = this.getFiles();
    // we can also reinforce hash with a specific version
    Hash#W# hash = new Hash#W#();
    for (int i = 0; i < fs.length; i++) {
      if (hash.match(fs[i], h)) {
        return true;
      }
    }
    return false;
  }
}
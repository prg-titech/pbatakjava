public class HashValue ver 2 {
  private int val;
  public HashValue(int val) {
    this.val = val;
  }
  public int getVal() {
    return this.val;
  }
  public boolean equals(HashValue o) {
    return this.getVal() == o.getVal();
  }
}

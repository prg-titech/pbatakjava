public class Point ver 3 {
  public int x;
  public int y;
  public Point(int x, int y) {
    this.x = x; this.y = y;
  }
  public «2 <= V» Point#V# copy() {
    return new Point#V#(this.x, this.y);
  }
}

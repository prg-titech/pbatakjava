public class Point ver 2 {
  public int x;
  public int y;
  public Point(int x, int y) {
    this.x = x; this.y = y;
  }

  /**
   * public Point inverse() {
   *   return V.make(y, x);
   * }
   */

  public «V» Point#V# inverse() {
    return new Point#V#(y, x);
  }
}

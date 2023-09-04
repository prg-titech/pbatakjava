public class Line ver 1 «V» {
  public Point#V# p1;
  public Point#V# p2;
  public Line(Point#V# p1, Point#V# p2) {
    this.p1 = p1; this.p2 = p2;
  }
  public void call(int newX) {
    p1.update(newX);
    // p2.modify(newX);
  }
}
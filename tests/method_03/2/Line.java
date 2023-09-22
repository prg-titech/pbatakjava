public class Line ver 2 «V,W» {
  public Point#V# p1;
  public Point#W# p2;
  public Line(Point#V# p1, Point#W# p2) {
    this.p1 = p1; this.p2 = p2;
  }
  public «Y» Line«V,Y» rotate() {
    return new Line«V,Y»(p1, p2.«Y»inverse());
  }
}
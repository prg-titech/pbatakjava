public class Line ver 1 «V,W» {
  public Point#V# p1;
  public Point#W# p2;
  public Line(Point#V# p1, Point#W# p2) {
    this.p1 = p1; this.p2 = p2;
  }
}

/**
 * public interface Line {
 * 
 * }
 * 
 * public class Line_v1 implements Line {
 *   public Point_Factory V;
 *   public Point_Factory W;
 *   public Point p1;
 *   public Point p2;
 *   public Line_v1(Point_Factory V, Point_Factory W
 *                  Point p1, Point p2) {
 *     this.V = V;
 *     this.W = W;
 *     this.p1 = p1;
 *     this.p2 = p2;
 *   }
 * }
 * 
 * public interface Line_Factory {
 *   public Line make(Point_Factory V, Point_Factory W,
 *                    Point p1, Point p2);
 * }
 * 
 * public class Line_v1_Factory implements Line_Factory {
 *   public Line make(Point_Factory V, Point_Factory W,
 *                    Point p1, Point p2) {
 *     return new Line_v1(V, W, p1, p2);
 *   }
 * }
 */
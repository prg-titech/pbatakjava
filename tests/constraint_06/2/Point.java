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



/**
 * public interface Point_Factory {
 *   public Point make(int x, int y);
 *   public Point inverse(Point p);
 * }
 * 
 * public class Point_v2_Factory implements Point_Factory {
 *   public Point make(int x, int y) { return new Point_v2(x, y); }
 *   public Point inverse(Point p) { return ((Point_v2) p).inverse(this); }
 * }
 * 
 * public class Point_v1_Factory implements Point_Factory {
 *   public Point make(int x, int y) { return new Point_v1(x, y); }
 *   public Point inverse(Point p) { return nullptr; }
 * }
 * 
 * up won't work because we might have multiple arguments
 * 
 * down is the better option, although this will make code bloats
 * 
 * public interface Point {
 *   public Point inverse(Point_Factory V);
 * }
 * 
 * public class Point_v2 implements Point {
 *   public int x;
 *   public int y;
 *   public Point_v2(int x, int y) {
 *     this.x = x;
 *     this.y = y;
 *   }
 *   public Point inverse(Point_Factory V) { return V.make(y, x); }
 * }
 * 
 * public class Point_v1 implements Point {
 *   public int x;
 *   public int y;
 *   public Point_v2(int x, int y) {
 *     this.x = x;
 *     this.y = y;
 *   }
 *   public Point inverse(Point_Factory V) { return nullptr; }
 * }
 */
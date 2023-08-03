public class Point ver 1 {
  private int x;
  private int y;
  public Point(int x, int y) {
    this.x = x; this.y = y;
  }
}

/**
 * public interface Point {
 * 
 * }
 * 
 * public class Point_v1 implements Point {
 *   public int x;
 *   public int y;
 *   public Point_v1(int x, int y) {
 *     this.x = x;
 *     this.y = y;
 *   }
 * }
 * 
 * public interface Point_Factory {
 *   public Point make(int x, int y);
 * }
 * 
 * public class Point_v1_Factory implements Point_Factory {
 *   public Point make(int x, int y) {
 *     return new Point_v1(x, y);
 *   }
 * }
 */
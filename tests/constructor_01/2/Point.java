public class Point ver 2 {
  public int x;
  public int y;
  public int z;
  public Point(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
}

/**
 * 
 * public interface Point {}
 * 
 * public class Point_v2 implements Point {
 *   public int x;
 *   public int y;
 *   public int z;
 *   public Point_v2(int x, int y, int z) {
 *     this.x = x ; this.y = y; this.z = z;
 *   }
 * }
 * 
 * // gather everything
 * public interface Point_Factory {
 *   public Point make(int x, int y);
 *   public Point make(int x, int y, int z);
 * }
 * 
 * public class Point_v2_Factory implements Point_Factory {
 *   public Point make(int x, int y) {
 *     return null;
 *   }
 *   public Point make(int x, int y, int z) {
 *     return new Point_v2(x, y, z);
 *   }
 * }
 * 
 */
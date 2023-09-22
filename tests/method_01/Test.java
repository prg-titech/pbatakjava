public class Test ver 1 {
  public static void main(String[] args) {
    Line l1 = new Line«1,2»(new Point(1,1), new Point(2,2));
    Line l2 = l1.«2»update(new Point(2,2));
  }
}

/**
 * public class Test_v1 implements Test {
 *  public static void main(String[] args) {
 *    Line_v1 l1 = new Line_v1(new Point_v1_Factory(), new Point_v2_Factory(), new Point_v1(1, 1), new Point_v2(2, 2));
 *    Line_v1 l2 = l1.update(new Point_v2_Factory(), new Point_v2(2, 2));
 *  }
 * } 
 */
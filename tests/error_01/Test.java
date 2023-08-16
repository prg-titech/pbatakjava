public class Test ver 1 {
  public static void main(String[] args) {
    Point p1 = new Point(2,2);
    p1.m();
    // MAYBE: make better error messages with some
    //        sort of local analysis
    Line l1 = new Line«2,2»(p1, new Point(2,2));
  }
}

public class Test ver 1 {
  // p1 can't be assigned with version argument 2
  public static void main(String[] args) {
    Point p1 = new Point(2,2);
    p1.m();
    Line l1 = new Line«2,2»(p1, new Point(2,2));
  }
}

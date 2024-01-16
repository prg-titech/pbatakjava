public class Test {
  public static void main(String[] args) {
    Line l1 = new Line«1,1»(new Point(), new Point());
    Point p = l1.p1;
    l1.p2 = new Point();
  }
}
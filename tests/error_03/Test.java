public class Test ver 1 {
  // version argument check
  public static void main(String[] args) {
    Line l1 = new Line«1,2»(new Point(1,1), new Point(2,2));
    Line l2 = l1.«2,1»update(new Point(2,2));
    new Point(1,1).m();
  }
}
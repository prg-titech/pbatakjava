public class ColoredPoint ver 1 extends Point#1# {
  public Object c;
  public ColoredPoint(int x, int y, Object c) {
    super(x, y);
    this.c = c;
  }
  // just to check constraint
  public int getX() {
    return -super.getX();
  }
}

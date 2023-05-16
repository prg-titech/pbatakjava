class Line ver 1 >V,W< {
  Point#V# p1;
  Point#W# p2;
  Line(Point#V# p1, Point#W# p2) {
    this.p1 = p1;
    this.p2 = p2;
  }
  >Y< Line>V,Y< update(Point#Y# p) {
    return new Line>V,Y<(p1, p);
  }
  >Y< void update() {
    this.p1 = new Point#V#>Y<(new Color());
  }
}

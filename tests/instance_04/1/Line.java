class Line ver 1 «V,W» {
  public Point#V# p1;
  public Point#W# p2;
  public Line(Point#V# p1, Point#W# p2) {
    this.p1 = p1;
    this.p2 = p2;
  }
  public «Y» Line«V,Y» update(Point#Y# p) {
    return new Line«V,Y»(p1, p);
  }
  public «Y» void update() {
    this.p1 = new Point#V#«Y»(new Color());
  }

  /*
   * public void update(Color_Factory Y) {
   *   this.p1 = V.make(Y, new Color_v1());
     }
   */
}

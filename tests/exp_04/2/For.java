public class For ver 2 «V» implements Stmt#2# {
  public Integer start;
  public Integer end;
  public Stmt#V# body;
  public For(Integer start, Integer end, Stmt#V# body) {
    this.start = start;
    this.end = end;
    this.body = body;
  }
  public void interp(Env env) {
    for (int i = this.start; i < this.end; i++) {
      this.body.interp(env);
    }
    // for i in range(0,n)
    // i \in [0,n)
  }
}

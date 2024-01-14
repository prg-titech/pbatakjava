public class For ver 1 «V» implements Stmt#1# {
  public Integer start;
  public Integer end;
  public Stmt#V# body;
  public For(Integer start, Integer end, Stmt#V# body) {
    this.start = start;
    this.end = end;
    this.body = body;
  }
  public void interp(Env env) {
    for (int i = this.start; i <= this.end; i++) {
      this.body.interp(env);
    }
    // for i in range(0,n)
    // i \in [0,n]
  }
}


// 1. 

// there is a library (list, graph)
// there is a fast version, feature is limited
// there is a original version, slower but more features

// convert everything? use faster for specific part?

// 2.
// in the situation where legacy code can't be removed

// 3.
// eval, print entire mixed version code

// 4 
// ant's update introduces new build task, new feature

// 5
// language -> language + new construct
//   |
//   v
// analysis tool -> analysis tool (+ new construct preferable without changing the tool)


// 6
// distributed computing?
// a server with different users of different versions
public class Env ver 2 {
  public java.util.Map<String,Integer> env;
  public Env() {
    this.env = new java.util.HashMap<String,Integer>();
  }
  public void set(String var, Integer val) {
    this.env.put(var, val);
  }
  public int get(String var) {
    return this.env.get(var);
  }
  public boolean has(String var) {
    return this.env.containsKey(var);
  }
}

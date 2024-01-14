public class App {
  private boolean processID(String n) {
    // using MD5 algorithm
    return new Dir«1,1»().exists(new Hash().mkHash(n));
  }
  
  private boolean processLog(String n) {
    // using SHA algorithm
    return new Dir«2,2»().exists(new Hash().mkHash(n));
  }
}

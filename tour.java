import java.util.*;
public class tour {
  private ArrayList<Integer> com = new ArrayList();
  public tour(int rI, int cI) {
    com.add(rI);
    com.add(cI);
  }

  public int gR() {
    return com.get(0);
  }
  public int gC() {
    return com.get(1);
  }

  public String toString() {
    String out = "" + gR() + "," + gC();
    return out;
    }
  public static void main(String[] args) {
    tour a = new tour(1,2);
    System.out.println(a);
  }
}

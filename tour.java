import java.util.*;
public class tour implements Comparable<tour> {
  private ArrayList<Integer> com = new ArrayList<>(2);
  public tour(int rI, int cI) {
    com.add(rI);
    com.add(cI);;
  }

  public int gR() {
    return com.get(0);
  }
  public int gC() {
    return com.get(1);
  }

  public String toString() {
    return "{" + gR() + "," + gC() + "}";
    }

  public int compareTo(tour other) {
    if (gR() == other.gR() && gC() == other.gC()) return 0;
    else return -1;
  }
}

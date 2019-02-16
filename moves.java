import java.util.*;
public class moves {
  private ArrayList<tour> mov = new ArrayList<>();
  public moves(){
    mov.add(new tour(1,2));
    mov.add(new tour(-1,2));
    mov.add(new tour(1,-2));
    mov.add(new tour(-1,-2));
    mov.add(new tour(2,1));
    mov.add(new tour(2,-1));
    mov.add(new tour(-2,1));
    mov.add(new tour(-2,-1));

  }

  public String toString() {
    String out = "[";
    for (int i = 0; i < 8; i++) {
      if (i != 7 )out += mov.get(i) + ", ";
      else out +=mov.get(i);
    }
    return out + "]";
  }

  public int find(int rI, int cI) {
    for (int i = 0; i < 8; i++) {
      if (mov.get(i).gR() == rI && mov.get(i).gC() == cI) return i;
    }
    return -1;
  }

  public void sortM(tour a) {
    mov.add(0, mov.remove(find(a.gR(), a.gC())));
  }
  public static void main(String[] args) {
    moves a = new moves();
    System.out.println(a);
    System.out.println(a.find(-2,-1)); // 7
    a.sortM(new tour(-2,-1));
    System.out.println(a);
  }
}


public class KnightBoard{
  private int[][] board;
  private int stR, stC;
  private boolean stat;
  private tour[] a = {
    new tour(1,2), new tour(2,1), new tour(-2, -1), new tour(-1,-2),
    new tour(-1,2), new tour(2, -1), new tour(-2, 1), new tour(1,-2)
  };
  /*
  Initialize the board to the correct size and make them all 0's
  @throws IllegalArgumentException when either parameter is negative.
  */
  public KnightBoard(int startingRows,int startingCols){
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException();
    }
    stR = startingRows;
    stC = startingCols;
    board = new int[stR][stC];
  }

  /*displays the board*/
  public String toString(){
    String out = "";
    for (int i = 0; i < stR; i++) {
      for (int j = 0; j < stC; j++) {
        if (board[i][j] == 0) out += "__ ";
        else if (board[i][j] < 10) out += " " + board[i][j] + " ";
        else out += board[i][j] + " ";
      }
      out += "\n";
    }
    return out;
  }
    /*@throws IllegalStateException when the board contains non-zero values.*/
  private void empty() {
    for (int i = 0; i < stR; i++) {
      for (int j = 0; j < stC; j++) {
        if (board[i][j] != 0) {
          throw new IllegalStateException();
        }
      }
    }
  }

  /*@throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  private void runOff(int startingRow, int startingCol) {
     if (startingRow < 0 ||
         startingCol < 0 ||
         startingRow >= stR ||
         startingCol >= stC) {
       throw new IllegalArgumentException();
     }
   }

  public boolean solve(int startingRow, int startingCol){
    runOff(startingRow, startingCol);
    empty();
    boolean x = solveH(startingRow, startingCol, 1);
    if (!stat) clear();
    return x;
  }

  public int countSolutions(int startingRow, int startingCol){
    runOff(startingRow, startingCol);
    empty();
    return counter(startingRow, startingCol, 1);
  }

  //simplifies solutions --> only if available can we continue
  private boolean avail(int r, int c, int level) {
    if(r + 1 < stR && c + 2 < stC) {
      if (solveH(r + 1, c + 2, level)) return true;
    }
    if(r + 2 < stR && c + 1 < stC) {
      if (solveH(r + 2, c + 1, level)) return true;
    }
    if(c - 1 >= 0 && r + 1 < stR) {
      if (solveH(r + 1, c - 1, level)) return true;
    }
    if(c - 2 >= 0 && r + 1 < stR){
      if (solveH(r + 1, c -2, level)) return true;
    }
    if(r - 1 >= 0 && c + 2 < stC){
      if (solveH(r - 1, c + 2, level)) return true;
    }
    if(r - 2 >= 0 && c + 1 < stC) {
      if (solveH(r - 2, c + 1, level)) return true;
    }
    if(r - 1 >= 0 && c - 2 >= 0) {
      if (solveH(r - 1, c - 2, level)) return true;
    }
    if(r - 2 >= 0 && c - 1 >= 0) {
      if (solveH(r - 2, c - 1, level)) return true;
    }
    return false;
  }
  /*private tour[] sortMoves (int row, int col) {
    tour[] temp = a;
    tour[] ans;
    if (row == 0) {
      if (col == 0) {
        ans = new tour[] {new tour(2, 1), new tour(1,2)};
      }
      if (col == stC -1) {
        ans = new tour[] {new tour(-1, -2), new tour(-2,-1)};
      }
      if (col == 1) {
        ans = new tour[] {new tour(1,2), new tour(2, 1), new tour(2,-1)};
      }
      if (col == stC -2) {
        ans = new tour[] {new tour(2,1), new tour(2, -1), new tour(1, -2)};
      }
      else {
        ans = new tour[] {new tour(2, 1), new tour(2, -1),
                        new tour(1, -2), new tour(1, 2)};
      }
    }
    if (row == stR -1) {
      if (col == 0) {
        ans = new tour[] {new tour(-1, 2), new tour(-2, 1)};
      }
      if (col == stC -1) {
        ans = new tour[] {new tour(-2, -1), new tour(-1, -2)};
      }
      if (col == 1) {
        ans = new tour[] {new tour(-1, 2), new tour(-2, 1), new tour(-2, -1)};
      }
      if (col == stC -2) {
        ans = new tour[] {new tour(-2, -1), new tour(-2, 1), new tour(-1, -2)};
      }
      else {
        ans = new tour[] {new tour(-1, -2), new tour(-1, 2),
                          new tour(-2, 1), new tour(-2, -1)};

      }
    }
    if (row == 1) {
      if (col == 1) {
        ans = new tour[] {new tour(-1, 2), new tour(1,2), new tour(2, 1), new tour(2, -1)};
      }
      if (col == stC -2) {
        ans = new tour[] {new tour(1, -2), new tour(-1, -2), new tour(2, -1), new tour(2, 1)};
      }
    }
    if (row == stR -2) {
      if (col == 1) {
      ans = new tour[] {new tour(-1, 2), new tour(1,2), new tour(-2, 1), new tour(-2, -1)};
      }
      if (col == stC -2) {
        ans = new tour[] {new tour(-1, -2), new tour(1, -2), new tour(-2, -1), new tour(-2, 1)};
      }
    }
    if (col == 0) {
      if (row == 1) {
        ans = new tour[] {new tour(-1, 2), new tour(1, 2), new tour(2, 1)};
      }
      if (row == stR -2) {
        ans = new tour[] {new tour(-1, 2), new tour(1,2), new tour(-2, 1)};
      }
    }
    if (col == stC -1) {
      if (row == 1) {
        ans = new tour[] {new tour(-1, -2), new tour(1, -2), new tour(-2, -1)};
      }
      if (row == stC -2) {
        ans = new tour[] {new tour(-1, -2), new tour(1,-2), new tour(2, -1)};
      }
    }
    if (col == 0 && row != 0 && row != stR -1
    && row != 1 && row != stR -2) {
      ans = new tour[] {new tour(-2, 1), new tour(2, 1),
                        new tour(-1, 2), new tour(1, 2)};
    }
    if (col == stC-1 && row != 0 && row != stR -1 &&
        row != 1 && row != stR -2) {
      ans = new tour[] {new tour(-2, -1), new tour(2, -1),
                        new tour(-1, -2), new tour(1, -2)};
    }
    else ans = temp;
    return ans;
  }
*/
  // tail end recursion for counting solutions
  private int counter(int row, int col, int level) {
    int sum = 0;
    if (level == stR* stC) return 1;
    //tour[] s = sortMoves(row,col);
    for (int i = 0; i < a.length; i++) {
      try {
        if (board[row + a[i].gR()][col + a[i].gC()] == 0) {
          board[row][col] = level;
          sum += counter(row + a[i].gR(), col +a[i].gC(), level + 1);
          board[row][col] = 0;
        }
      }catch (Exception e) {}
      }
      return sum;
  }
  //tail end recursion for touring through
  private boolean solveH(int row ,int col, int level) {
    if (row < 0 || col < 0 || row >= stR || col >= stC) return false;
    if (level > stR * stC) {
      stat = true;
      return true;
    }
    if (board[row][col] == 0){
      board[row][col] = level;
      /*tour[] s = sortMoves(row,col);
      for (int i = 0; i < s.length; i++) {
        if (solveH(row + s[i].gR(), col + s[i].gC(), level + 1)) return true;*/
      level += 1;
      if(avail(row, col, level)) return true;
      else board[row][col] = 0;
      }
    return false;
  }

  //just  for updating correctly (kind of like a remove so to speak from queens)
  /*private void reset (int level) {
    for (int i = 0; i < stR; i++) {
      for (int j = 0; j < stC; j++) {
        if (board[i][j] > level) board[i][j] = 0;
      }
    }
  }*/

  private void clear() {
    for (int i = 0; i < stR; i++) {
      for (int j = 0; j < stC;j++) {
        board[i][j] = 0;
      }
    }
  }

  public static void main(String[] args) {
    /*KnightBoard a = new KnightBoard(7, 4);
    //System.out.println(a);
    System.out.println(a.solve(0,0));
    System.out.println(a);
    KnightBoard b = new KnightBoard(5,5);
    System.out.println(b);
    //System.out.println(b.solve(0,0));
    //System.out.println(b);
    System.out.println(b.countSolutions(0,0)); //304
    System.out.println(b.countSolutions(0,1)); // 0 no solution
    System.out.println(b.solve(0,1)); //false
    System.out.println(b.countSolutions(0,2)); //56
    System.out.println(b.countSolutions(2,2)); //64
    System.out.println(b.solve(1,2)); // false
    System.out.println(b.countSolutions(1,2)); // 0 no solution
    KnightBoard c = new KnightBoard(6,6);
    System.out.println(c.solve(0,0));
    System.out.println(c);
    //System.out.println(c.countSolutions(0,0));*/
    KnightBoard d = new KnightBoard(7,7);
    System.out.println(d.solve(0,0));
    System.out.println(d);
    //System.out.println(d.countSolutions(0,0));
    KnightBoard e = new KnightBoard(8,8);
    System.out.println(e.solve(0,0));
    System.out.println(e);
    //System.out.println(d.countSolutions(0,0));*/

  }
}

import java.util.*;
public class KnightBoard {
  private int[][] board;
  List<tour>[][] pM; //possible Moves
  int mR, mC; //max Row and Col
  final static tour[] moves = new tour[] { new tour(-1, -2), new tour(1, -2), new tour(2, -1), new tour(2, 1),
                                           new tour(1,2), new tour(-1, 2), new tour(-2, 1), new tour (-2, -1) };

  private boolean runOff(int row, int col) {
    return !(row < 0 || col < 0 || row >= mR || col >= mC);
  }
  private void clear() {
    for (int i = 0; i < mR; i++) {
      for (int j = 0; j < mC;j++) {
        board[i][j] = 0;
      }
    }
  }
  /*@throws IllegalStateException when the board contains non-zero values.*/
  private void empty() {
    for (int r = 0; r < mR; r++) {
      for (int c = 0; c < mC; c++) {
        if (board[r][c] != 0) {
          throw new IllegalStateException();
        }
      }
    }
  }
  /*displays the board*/
  public String toString(){
    String out = "";
    for (int r = 0; r < mR; r++) {
      for (int c = 0; c < mC; c++) {
        if (board[r][c] == 0) out += "___ ";
        else if (board[r][c] < 10) out += "  " + board[r][c] + " ";
        else if (board[r][c] < 100) out += " " + board[r][c] + " ";
        else out += board[r][c] + " ";
      }
      out += "\n";
    }
    return out;
  }
  /*
  Initialize the board to the correct size and make them all 0's
  @throws IllegalArgumentException when either parameter is negative.
  */
  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException();
    }
    mR = startingRows;
    mC = startingCols;
    board = new int[mR][mC];
    pM = new List[mR][mC];

    for (int r = 0; r < mR; r++) {
      for (int c = 0; c < mC; c++) {
        board[r][c] = 0;
        pM[r][c] = new ArrayList<>();

        for (tour a : moves) {
          //the n starts for new posiiton
          int nR = r + a.gR();
          int nC = c + a.gC();
          //only add to possibleMoves if THEY CAN be placed on baord
          if ((nR >= 0 && nR < mR) && (nC >= 0 && nC < mC)) {
            pM[r][c].add(new tour(nR, nC));
          }
        }
      }
    }
    prePM();

  }
  // sorts the possible moves so we get the least amount of possible moves : through suggestions on the best way to handle this: have a list containing the possible moves
  private void prePM(){
    for (int r = 0; r < mR; r++) {
      for (int c = 0; c < mC; c++) {
        List<tour> moves = pM[r][c];
        moves.sort(new Comparator<tour>() // had to look up the documentation for this OH MY GOD JESUS NO
        {
          // probably the MOST ANNOYING THING EVER YET SO SIMPLE JESUS HAD TO DRAW THIS OUT TO FIGURE WHERE I WAS SORTING HAD TO LOOK UP IF I COULD DO SOMETHING LIKE THIS OH MER GAD I HATE MY LIFE BLESS ETHAN FOR EXPLAINING I COULD DO THIS ISDOHVNBOVFVBOD
          public int compare(tour a, tour b) {
            int aM = pM[a.gR()][a.gC()].size();
            int bM = pM[b.gR()][b.gC()].size();
            return aM - bM;
          }
        });
      }
    }
  }
  //same old same old BUT directly clears this time in worst case scenario
  public boolean solve(int startingRow, int startingCol) {
    if (!runOff(startingRow, startingCol)) throw new IllegalArgumentException();
    empty();
    boolean solved = solveH(startingRow, startingCol, 1);
    if (!solved) {
      clear();
      return false;
    }
    return solved;
  }
  //tail end recursion for row\col\level for solve
  private boolean solveH(int row, int col, int level) {
    if (board[row][col] != 0) return false;
    board[row][col] = level;
    if (level == mR * mC) return true;
    for (tour s: pM[row][col]) {
      //just use the premade pM moves to quickly get an answer
      if (solveH(s.gR(), s.gC(), level + 1)) {
        return true;
      }
    }
    //we got nothing if we come here
    board[row][col] = 0;
    return false;
  }
  //counts the number of total solutions from given position
  public int countSolutions(int startingRow, int startingCol) {
    if (!runOff(startingRow, startingCol)) throw new IllegalArgumentException();
    empty();
    return counter(startingRow, startingCol, 1);
  }
  //thank god this only goes up to 6 I actually tried to deal with this for higher numbers and JUST NOPE BYE NO MORE FREEEE
  private int counter(int row, int col, int level) {
    int sum = 0;
    if (level == mR * mC) return 1;
    for (int i = 0; i < moves.length; i++) {
      try {
        if (board[row + moves[i].gR()][ col + moves[i].gC()] == 0) {
          board[row][col] = level;
          sum += counter(row + moves[i].gR(), col + moves[i].gC(), level + 1);
          board[row][col] = 0;
        }
      }catch (Exception e) {}
    }
    return sum;

  }
}

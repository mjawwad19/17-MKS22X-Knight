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
    if (startingRows < 0 || startingCols < 0) {
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
  private boolean solve(int startingRow, int startingCol) {
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
  //testcase must be a valid index of your input/output array
public static void runTest(int i){

  KnightBoard b;
  int[]m =   {4,5,5,5,5};
  int[]n =   {4,5,4,5,5};
  int[]startx = {0,0,0,1,2};
  int[]starty = {0,0,0,1,2};
  int[]answers = {0,304,32,56,64};
  if(i >= 0 ){
    try{
      int correct = answers[i];
      b = new KnightBoard(m[i%m.length],n[i%m.length]);

      int ans  = b.countSolutions(startx[i],starty[i]);

      if(correct==ans){
        System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
      }
    }catch(Exception e){
      System.out.println("FAIL Exception case: "+i);

    }
  }
}


  public static void main(String[] args) {
  /*  KnightBoard t = new KnightBoard(1,1);
      System.out.println(t.countSolutions(0,0)); //1
      System.out.println(t.solve(0,0)); //true duh
      System.out.println(t);
    KnightBoard u = new KnightBoard(2,2);
      System.out.println(u.solve(0,0)); //false
      System.out.println(u);
    KnightBoard v = new KnightBoard(3,3);
      System.out.println(v.solve(0,0)); //false
      System.out.println(v);
    KnightBoard w = new KnightBoard(4,4);
      System.out.println(w.solve(0,0)); //false
      System.out.println(w);
    KnightBoard x = new KnightBoard(5,5);
      System.out.println(x.countSolutions(0,0)); //304
      System.out.println(x.countSolutions(0,1)); // 0 no solution
      System.out.println(x.solve(0,1)); //false
      System.out.println(x.countSolutions(0,2)); //56
      System.out.println(x.countSolutions(2,2)); //64
      System.out.println(x.solve(1,2)); // false
      System.out.println(x.countSolutions(1,2)); // 0 no solution
      //System.out.println(x.solve(7,7)); // error message IllegalArgumentException
      System.out.println(x.solve(0,0));
      //System.out.println(x.countSolutions(0,0)); //error message IllegalStateException
      System.out.println(x);
    KnightBoard a = new KnightBoard(6,6);
      //System.out.println(a.countSolutions(0,0)); stuck for entirity aka more than 15 seconds cuz there's too much ;-;
      a.solve(0,0);
      System.out.println(a);
    KnightBoard b = new KnightBoard(7,7);
      b.solve(0,0);
      System.out.println(b);
    KnightBoard c = new KnightBoard(8,8);
      c.solve(0,0);
      System.out.println(c);
    KnightBoard d = new KnightBoard(9,9);
      d.solve(0,0);
      System.out.println(d);
    KnightBoard e = new KnightBoard(10,10);
      e.solve(0,0);
      System.out.println(e);
    KnightBoard f = new KnightBoard(12,12);
      System.out.println(f.solve(0,0));
      System.out.println(f);
    //Halle-friggin-luyah*/
    for (int i = 0; i < 5; i++) {
      runTest(i); //passed all;
    }
  }
}

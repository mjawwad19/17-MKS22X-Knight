public class KnightBoard{
  private int[][] board;
  private int[][] moves = {{1,2},
                          {-1,2},
                          {-1,-2},
                          {1,-2},
                          {2,1},
                          {2, -1},
                          {-2, 1},
                          {-2, -1}}; //the possible moves in any direction
  /*
  Initialize the board to the correct size and make them all 0's
  @throws IllegalArgumentException when either parameter is negative.
  */
  public KnightBoard(int startingRows,int startingCols){
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
  }

  /*displays the board*/
  public String toString(){
    String out = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (empty(i,j)) out += "__ ";
        else if (board[i][j] < 10) out += " " + board[i][j] + " ";
        else out += board[i][j] + " ";
      }
      out += "\n";
    }
    return out;
  }

  //actual place in board?
  private boolean runOff(int row, int col) {
    if (row < 0 ||
        col < 0 ||
        row >= board.length ||
        col >= board[0].length) return true;
    return false;
  }
  //empty place on board?
  private boolean empty(int row, int col) {
    return board[row][col] == 0;
  }
  /*@throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  private void solSumH(int startingRow, int startingCol) {
    if (runOff(startingRow, startingCol)) throw new IllegalArgumentException();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (!empty(i,j)) throw new IllegalStateException();
      }
    }
  }

  public boolean solve(int startingRow, int startingCol){
    solSumH(startingRow, startingCol);
    return solveH(startingRow, startingCol, 1);
  }

  public int countSolutions(int startingRow, int startingCol){
    solSumH(startingRow, startingCol);
    return counter(startingRow, startingCol, 1);
  }

  // tail end recursion for counting solutions
  private int counter(int row, int col, int level) {
    int sum = 0;
    if (level == board.length* board[0].length) return 1;
    for (int move[] :moves) {
      try {
        if (empty(row + move[0], col+ move[1])) {
          board[row][col] = level;
          sum += counter(row + move[0], col +move[1], level + 1);
          board[row][col] = 0;
        }
      }catch (Exception e) {}
      }
      return sum;
  }
  //tail end recursion for touring through
  private boolean solveH(int row ,int col, int level) {
    if (runOff(row, col)) return false;
    if (level > board.length * board[0].length) return true;
    boolean out = false;
    if (empty(row, col)){
      board[row][col] = level;
      for (int[] move : moves) {
        if (solveH(row + move[0], col + move[1], level + 1)) return true;
        reset(level);
      }
    }
    if (!out) reset(level-1); //for false cases the original level is still  need remove
    return out;
  }

  //just  for updating correctly (kind of like a remove so to speak from queens)
  private void reset (int level) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] > level) board[i][j] = 0;
      }
    }
  }
  // level is the # of the knight


  public static void main(String[] args) {
    KnightBoard a = new KnightBoard(7, 4);
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
    System.out.println(b.countSolutions(1,2)); // 0 no solution*/
    KnightBoard c = new KnightBoard(6,6);
    System.out.println(c.solve(0,0));
    System.out.println(c);
    //System.out.println(c.countSolutions(0,0));
  }
}

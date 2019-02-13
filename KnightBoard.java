public class KnightBoard{
  private int[][] board;
  private int[][] moves = {{2, 1},{-2, 1}, {2, -1}, {-2,-1}, {1, 2}, {-1,-2}, {-1, 2}, {1, -2}};
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

  public String toString(){
    String out = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 0) out += "_ ";
        else if (board[i][j] < 10) out += " " + board[i][j] + " ";
        else out += board[i][j] + " ";
      }
      out += "\n";
    }
    return out;
  }

  /*@throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  public boolean solve(int startingRow, int startingCol){
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length -1 || startingCol > board[0].length - 1) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != 0) {
          throw new IllegalStateException();
        }
      }
    }
    /*if (startingRow <= startingCol) {
      if (startingCol%2 == 1 && startingRow%2 == 1 ||
          startingRow == 1 ||
          startingRow == 2 ||
          startingRow == 4 ||
          startingRow == 3 && (startingCol == 4 ||
                              startingCol == 6 ||
                              startingCol == 8)) {
        return false;
      }
    }*/
    return solveH(startingRow, startingCol, 1);
  }

  /*@throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  //public int countSolutions(int startingRow, int startingCol){}

  private boolean solveH(int row ,int col, int level){
          for (int[] mov: moves) {
            int nR, nC;
            if (row + mov[0] >= 0 && row + mov[0] < board.length &&
                col + mov[1] >= 0 && col + mov[1] < board[0].length) {
                  nR = row + mov[0];
                  nC = col + mov[1];
                  if (board[nR][nC] == 0) {
                    board[nR][nC] = level + 1;
                    if (solveH(nR, nC, level +1)) {
                      return true;
                    }
                  }
                  else {
                    solveH(nR, nC, level);
                  }
          }
        }
    return false;
  }

  // level is the # of the knight

  public static void main(String[] args) {
    KnightBoard a = new KnightBoard(5, 6);
    System.out.println(a);
    System.out.println(a.solve(0,0));
    //System.out.println(a);
  }
}

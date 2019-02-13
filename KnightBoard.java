public class KnightBoard{
  private int[][] board;
  /*
  Initialize the board to the correct size and make them all 0's
  @throws IllegalArgumentException when either parameter is negative.
  */
  public KnightBoard(int startingRows,int startingCols){}



  public String toString(){}

  /*@throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  public boolean solve(int startingRow, int startingCol){}

  /*@throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
   or out of bounds.*/
  public int countSolutions(int startingRow, int startingCol){}

  //Suggestion:
  private boolean solveH(int row ,int col, int level){}
  // level is the # of the knight

}

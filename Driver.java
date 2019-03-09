public class Driver {


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

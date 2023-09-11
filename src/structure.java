import javax.swing.text.Position;
import java.util.*;
import java.util.Scanner;

 class Structure {

    int n = 4;
    int m = 5;

     // S = space
     // W = wall
     // C = castle
     // K = king
    String[][] board = {
            {" ", " ", "K", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " "},
            {"U*", " ", "J", " ", "S", " ", "H"},
            {" ", " ", " ", " ", " ", " ", " "},
            {" ", " ", "M", " ", " ", " ", " "}
    };

    public Structure(){

    }

    public Structure(int n, int m, String [][] board) {
        this.n = n;
        this.m = m;
        this.board = board;
    }

    void printBoard(String[][] board) {

        for(int i=0; i<5; i++)
        {
            for(int j=0; j<7; j++) {

                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    ///////////checker function//////////////
   public List<position> indexes = new ArrayList<>();
    List <position> checkMoves(String[][] board) {
        indexes.clear();
        for (int i=0;i<4;i++) {
            for (int j=0; j<5;j++) {
                if (Objects.equals(board[i][j], "C")){
                   // System.out.println("\n# The castle place is ["+i+","+j+"]");
                    for (int k=j+1;k<5;k++){
                        if (Objects.equals(board[i][k], "S") || Objects.equals(board[i][k], "K")) {
                            position p = new position(i, k);
                            indexes.add(p);
                        }
                    }

                    for (int l=j-1;l>=0;l--) {
                        if (Objects.equals(board[i][l], "S") || Objects.equals(board[i][l], "K")) {
                            position p = new position(i, l);
                           indexes.add(p);
                        }
                    }
                }
            }
        }
       /* System.out.println("\n## Possible movements: ");
        for (int i=0;i<indexes.size();i++){
            System.out.println(indexes.get(i).i + ", " + indexes.get(i).j);
        }*/
        return indexes;
    }

     public String[][] deepCopy(String[][] board) {
         if (board == null) {
             return null;
         }
         String[][] copy = new String[board.length][];
         for (int i = 0; i < board.length; i++) {
             copy[i] = board[i].clone();
         }
         return copy;
     }

     List<String[][]> getNextState(String[][] board) {
        List<String[][]> next = new ArrayList<>();
        List<position> p = checkMoves(board);
         for (position n:p) {
             String[][] s = deepCopy(board);
           //  next.add(movement(s, n));
         }
        /* for (String[][] n:next) {
             System.out.println(Arrays.deepToString(n));
         }*/
         return next;
     }

    ////////////move function/////////////
    /*String[][]*/ void movement (String[][] board/*, position p*/){
       /* boolean search = indexes.contains(p);
        if (!search) {
            System.out.println("Invalid position!");
        }
        else {*/
           // p.i= checkDown(board,p.i, p.j);
            for (int i=0;i<5;i++) {
                for (int j=0; j<7;j++) {
                   int index = board[i][j].indexOf("*");
                    if (index == -1) {
                        System.out.println("The player not found");}
                    else { System.out.println("The player not found"+index);
                        board[i+1][j]="*";
                        board[i][j]="";
                        //board[p.i][p.j]=" *";
                    //}
                }
            } }
           // printBoard(board);

       // return board;
    }

    //check if there is a wall or space under the castle, because it can not stand on space
    int checkDown(String[][] board,int p1,int p2) {

        for (int i=p1+1;i<4;i++)
        {
            if (Objects.equals(board[i][p2], "W"))
                return i-1;
        }
        return 3;
    }

     boolean visit(ArrayList<String[][]> visit, String[][] s) {
         boolean flag;
         for (String[][] cell : visit) {
             flag = equals2D(cell, s);
             if (flag) return false;
         }
         return true;
     }

     public static boolean equals1D(String[] a1, String[] a2) {
         if (a1 == null ||  a2 == null || a1.length != a2.length) return false;

         for (int i = 0; i < a1.length; i++)
             if (!Objects.equals(a1[i], a2[i])) return false;
         return true;
     }

     public static boolean equals2D(String[][] a1, String[][] a2) {
         // if (a1 == a2) return true;

         if (a1 == null || a2 == null || a1.length != a2.length) return false;

         for (int i = 0; i < a1.length; i++)
             if (!equals1D(a1[i], a2[i])) return false;

         return true;
     }

    //the final state, when the king will be died, the player will win
    public boolean isFinal(String[][] board){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (Objects.equals(board[i][j], "K")) {
                    return false;
                }
            }
        }
        System.out.println("\n Done..! the king died * _ *");
        return true;
    }


}



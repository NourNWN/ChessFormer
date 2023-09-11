import java.util.*;
import java.util.Scanner;

 class Structure {

    int n = 4;
    int m = 5;
    String[][] board = new String[n][m];

    Scanner input = new Scanner(System.in);
    public Structure(){

    }

    public Structure(int n, int m, String [][] board) {
        this.n = n;
        this.m = m;
        this.board = board;
    }

    void printBoard(String[][] board) {

        for(int i=0; i<4; i++)
        {
            for(int j=0; j<5; j++) {

                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    ///////////checker function//////////////
    List<String> indexes= new ArrayList<>();
    void checkMoves(String[][] board) {
        indexes.clear();
        for (int i=0;i<4;i++) {
            for (int j=0; j<5;j++) {
                if (Objects.equals(board[i][j], "C")){
                    System.out.println("\n# The castle place is ["+i+","+j+"]");
                    for (int k=j+1;k<5;k++){
                        if (Objects.equals(board[i][k], "S") || Objects.equals(board[i][k], "K")) {
                            indexes.add("("+i+","+k+")");
                        }
                    }

                    for (int l=j-1;l>=0;l--) {
                        if (Objects.equals(board[i][l], "S") || Objects.equals(board[i][l], "K")) {
                            indexes.add(("("+i+","+l+")"));
                        }
                    }
                }
            }
        }
        //System.out.println();
        System.out.println("\n## Possible movements: " + indexes);
    }

    ////////////move function/////////////
    void movement (String[][] board){
        System.out.print("\nEnter the position you wont it: ");
        int p1 = input.nextInt();
        int p2 = input.nextInt();
        System.out.println("position(" + p1 + "," + p2 + ")");

        boolean search = indexes.contains("("+p1+","+p2+")");
        if (!search) {
            System.out.println("Invalid position!");
        }
        else {
            p1= checkDown(board,p1,p2);

            for (int i=0;i<4;i++) {
                for (int j=0; j<5;j++) {
                    if (Objects.equals(board[i][j], "C")) {
                        board[i][j]="S";
                        board[p1][p2]="C";
                    }
                }
            }
            printBoard(board);
        }
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

public class Main {

    public static void main(String[] args) {
        Structure level1 = new Structure();

        // S = space
        // W = wall
        // C = castle
        // K = king
        String[][] board = {
                {"S", "C", "S", "S", "S"},
                {"W", "W", "W", "S", "W"},
                {"S", "S", "S", "S", "S"},
                {"W", "W", "K", "W", "W"},
        };

        level1.printBoard(board);
        while (!level1.isFinal(board)) {
            level1.checkMoves(board);
            level1.movement(board);
        }
    }
}


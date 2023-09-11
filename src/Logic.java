import java.util.*;
public class Logic {
    Structure structure = new Structure();

    int manhattanDist(int X1, int Y1, int X2, int Y2) {
        return Math.abs(X2 - X1) + Math.abs(Y2 - Y1);
    }

    public int Heuristic(Structure current) {
        int heuristic = 0;
        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (current.board[i][j] == "C") {
                    x1 = i;
                    y1 = j;
                }
                if (current.board[i][j] == "K") {
                    x2 = i;
                    y2 = j;
                }
                heuristic = manhattanDist(x1, y1, x2, y2);
            }

        }
        System.out.print(heuristic + " \n");
        return heuristic;
    }

    public void BFS() {
        Queue<String[][]> queue = new LinkedList<>();
        ArrayList<String[][]> visited = new ArrayList<>();
        List<String[][]> L;
        String[][] q0;
        queue.add(structure.board);
        while (!queue.isEmpty()) {
            q0 = queue.poll();
            visited.add(q0);
            if (structure.isFinal(q0)) {
                break;
            }
            L = structure.getNextState(q0);
            for (String[][] n : L) {
                if (structure.visit(visited, n)) {
                    queue.add(n);
                }
            }
        }
        System.out.println("Visited nodes in BFS: " + visited.size());
    }

    public void DFS() {
        Stack<String[][]> stack = new Stack<>();
        ArrayList<String[][]> visited = new ArrayList<>();
        List<String[][]> L;
        String[][] q0;
        stack.push(structure.board);
        while (!stack.isEmpty()) {
            q0 = stack.pop();
            visited.add(q0);
            if (structure.isFinal(q0)) {
                break;
            }
            L = structure.getNextState(q0);
            for (String[][] n : L) {
                if (structure.visit(visited, n)) {
                    stack.push(n);
                }
            }
        }
        System.out.println("Visited nodes in DFS: " + visited.size());
    }

    public void Ucs(Structure structure) {
        PriorityQueue<State> queue = new PriorityQueue<>();
        ArrayList<String[][]> Visited = new ArrayList<>();
        List<String[][]> l;
        State s1 = new State(structure);
        s1.cost = 0;
        queue.add(s1);
        State n;
        System.out.println("\n\t*****UCS*****\n");
        while (!queue.isEmpty()) {
            n = queue.remove();
            if (Visited.isEmpty()) {
                Visited.add(n.current.board);
            }
            structure.printBoard(n.current.board);
            System.out.println();
            l = n.current.getNextState(n.current.board);
            if (n.parent == null) {

                n.cost = 0;

            } else {

                n.cost = 1;

            }
            if (n.current.isFinal(n.current.board)) {

                break;
            }
            for (String[][] C : l) {
                State ss = new State();
                if (n.current.visit(Visited, C)) {
                    ss.parent = n;
                    ss.current.board = C;
                    queue.add(ss);
                    Visited.add(C);
                }
            }
        }
        System.out.print("Visited nodes in UCS: " + Visited.size());
    }

    public void AStar(Structure structure) {
        PriorityQueue<State> queue = new PriorityQueue<>(State::compareTo);
        ArrayList<String[][]> Visited = new ArrayList<>();
        List<String[][]> list;
        State s1 = new State(structure);
        s1.cost = 0;
        queue.add(s1);
        State n;
        System.out.println("\n\t*****Astar*****\n");
        while (!queue.isEmpty()) {
            n = queue.remove();
                if (Visited.isEmpty()) {
                    Visited.add(n.current.board);
            }
            structure.printBoard(n.current.board);
            System.out.println();
            list = n.current.getNextState(n.current.board);
            if (n.parent == null) {
                n.cost = 0;
            } else {
                n.cost = 1;
            }
            if (n.current.isFinal(n.current.board)) {
                break;
            }
            for (String[][] C : list) {
                State ss = new State();
                if (n.current.visit(Visited, C)) {
                    ss.parent = n;
                    ss.current.board = C;
                    Heuristic(ss.current);
                    queue.add(ss);
                    Visited.add(C);
                }
            }
        }
        System.out.println("Visited nodes in Astar: " + Visited.size());
    }
}
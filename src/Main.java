public class Main {

    public static void main(String[] args) {
       Logic logic = new Logic();
       Structure structure=new Structure();

     /*   logic.BFS();
        logic.DFS();
        logic.Ucs(structure);
        logic.AStar(structure);*/
         structure.movement(structure.board);
    }

}


public class State implements Comparable<State> {
    State parent;
    Structure current = new Structure();
    int cost = 0;

    public State() {
    }

    State(Structure structure)
    {
        this.current = structure;
    }

    public int compareTo(State state) {
        if (this.parent != null && state.parent != null) {
            return this.parent.compareTo(state.parent);
        }
        return this.cost - state.cost;
    }
}

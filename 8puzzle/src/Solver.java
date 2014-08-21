import java.util.Comparator;
import java.util.LinkedList;

/**
* Use the following test client to read a puzzle from a file (specified as a command-line argument) and print the solution to standard output. 
*/
public class Solver {

    private static class SolverNode {
        private Board state;
        private int stepsTaken;
        private SolverNode previousState;

        public SolverNode(final Board state, final int stepsTaken, final SolverNode previousState) {
            this.state = state;
            this.stepsTaken = stepsTaken;
            this.previousState = previousState;
        }

        public Board getState() {
            return this.state;
        }
        public int getStepsTaken() {
            return this.stepsTaken;
        }
        public SolverNode getPreviousState() {
            return this.previousState;
        }
    }

    private static class ManhattanSolverNodeComparator implements Comparator<SolverNode> {
        public int compare(final SolverNode o1, final SolverNode o2) {
            return (o1.getState().manhattan()+o1.getStepsTaken()) - (o2.state.manhattan()+o2.getStepsTaken());
        }
    }

    private final SolverNode solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<SolverNode> queue = new MinPQ<SolverNode>(initial.dimension(), new ManhattanSolverNodeComparator());
        queue.insert(new SolverNode(initial, 0, null));
        MinPQ<SolverNode> alternateQueue = new MinPQ<SolverNode>(initial.dimension(), new ManhattanSolverNodeComparator());
        alternateQueue.insert(new SolverNode(initial.twin(), 0, null));
        SolverNode tmpSolution = null;
        SolverNode alternateSolution = null;
        while (tmpSolution == null && alternateSolution == null) {
            tmpSolution = Solver.stepForward(queue);
            alternateSolution = Solver.stepForward(alternateQueue);
        }
        this.solution = tmpSolution;
    }

    private static SolverNode stepForward(final MinPQ<SolverNode> queue) {
        final SolverNode currentNode = queue.delMin();
        if (currentNode.getState().isGoal()) {
            return currentNode;
        }
        for (final Board neighbor: currentNode.getState().neighbors()) {
            if (currentNode.getPreviousState() == null || !neighbor.equals(currentNode.getPreviousState().getState())) {
                queue.insert(new SolverNode(neighbor, currentNode.getStepsTaken()+1, currentNode));
            }
        }
        return null;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.solution != null;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (this.isSolvable()) {
            return this.solution.getStepsTaken();
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (this.isSolvable()) {
            final LinkedList<Board> steps = new LinkedList<Board>();
            SolverNode node = solution;
            while (node != null) {
                steps.addFirst(node.getState());
                node = node.previousState;
            }
            return steps;
        } else {
            return null;
        }
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

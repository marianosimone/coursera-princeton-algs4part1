import java.util.Comparator;
import java.util.LinkedList;

/**
* Use the following test client to read a puzzle from a file (specified as a command-line argument) and print the solution to standard output. 
*/
public class Solver {

    private class SolverNode {
        Board state;
        int stepsTaken;
        SolverNode previousState;

        SolverNode(final Board state, final int stepsTaken, final SolverNode previousState) {
            this.state = state;
            this.stepsTaken = stepsTaken;
            this.previousState = previousState;
        }
    }

    private class ManhattanSolverNodeComparator implements Comparator<SolverNode> {
        public int compare(final SolverNode o1, final SolverNode o2) {
            return (o1.state.manhattan()+o1.stepsTaken) - (o2.state.manhattan()+o2.stepsTaken);
        }
    }

    private final SolverNode solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<SolverNode> queue = new MinPQ<SolverNode>(initial.dimension(), new ManhattanSolverNodeComparator());
        queue.insert(new SolverNode(initial, 0, null));
        SolverNode tmpSolution = null;
        while (!queue.isEmpty()) {
            final SolverNode currentNode = queue.delMin();
            if (currentNode.state.isGoal()) {
                tmpSolution = currentNode;
                break;
            }
            for (final Board neighbor: currentNode.state.neighbors()) {
                if (currentNode.previousState == null || !neighbor.equals(currentNode.previousState)) {
                    queue.insert(new SolverNode(neighbor, currentNode.stepsTaken+1, currentNode));
                }
            }
        }
        this.solution = tmpSolution;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.solution != null;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (this.isSolvable()) {
            return this.solution.stepsTaken;
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
                steps.addFirst(node.state);
                node = node.previousState;
            }
            return steps;
        } else{
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

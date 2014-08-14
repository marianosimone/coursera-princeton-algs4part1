import java.lang.Iterable;
import java.lang.StringBuilder;

public class Board {
    private final int dimension;
    private final int[][] blocks;
    private boolean isGoal;
    private int zeroI;
    private int zeroJ;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.dimension = blocks.length;
        this.blocks = new int[this.dimension][this.dimension];
        this.isGoal = true;
        for (int i = 0; i < this.dimension; ++i) {
            this.blocks[i] = new int[this.dimension];
            for (int j = 0; j < this.dimension; ++j) {
                int value = blocks[i][j];
                this.blocks[i][j] = value;
                if (this.isGoal && value != 0 && value != expectedValue(this.dimension, i, j)) {
                    this.isGoal = false;
                }
                if (value == 0) {
                    this.zeroI = i;
                    this.zeroJ = j;
                }
            }
        }
    }

    static int expectedValue(final int dimension, final int i, final int j) {
        return (i*dimension+j)+1;
    }

    // board dimension N
    public int dimension() {
        return this.dimension;
    }

    // number of blocks out of place
    public int hamming() {
        return 0;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.isGoal;
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        return this;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) return false;
        final Board other = (Board)y;
        if (!(this.dimension == other.dimension)) return false;
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension; ++j) {
                if (this.blocks[i][j] != other.blocks[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension; ++j) {
                builder.append(this.blocks[i][j]).append(' ');
            }
            builder.setCharAt(builder.length()-1, '\n');
        }
        return builder.substring(0, builder.length()-1);
    }
}
import java.util.List;
import java.util.ArrayList;

public class Board {
    private final int dimension;
    final int[][] blocks;
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
        int distance = 0;
        if (!this.isGoal()) {
            for (int i = 0; i < this.dimension; ++i) {
                for (int j = 0; j < this.dimension; ++j) {
                    if ((this.blocks[i][j] != 0) && (this.blocks[i][j] != expectedValue(this.dimension(), i, j))) {
                        distance += 1;
                    }
                }
            }
        }
        return distance;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int distance = 0;
        if (!this.isGoal()) {
            for (int i = 0; i < this.dimension; ++i) {
                for (int j = 0; j < this.dimension; ++j) {
                    int value = this.blocks[i][j];
                    if (value != 0) {
                        distance += Math.abs(i-((value-1)/this.dimension)) + Math.abs(j-((value-1) % this.dimension));
                    }
                }
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.isGoal;
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int originI = this.zeroI-1;
        if (originI < 0) {
            originI = this.zeroI+1;
        }
        return this.cloneExchanging(originI, originI, 0, 1);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) return false;
        final Board other = (Board) y;
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
        final List<Board> neighbors = new ArrayList<Board>(4);
        if (zeroI > 0) {
            neighbors.add(this.cloneExchanging(this.zeroI, this.zeroI-1, this.zeroJ, this.zeroJ));
        }
        if (zeroI < this.dimension-1) {
            neighbors.add(this.cloneExchanging(this.zeroI, this.zeroI+1, this.zeroJ, this.zeroJ));
        }
        if (zeroJ > 0) {
            neighbors.add(this.cloneExchanging(this.zeroI, this.zeroI, this.zeroJ, this.zeroJ-1));
        }
        if (zeroJ < this.dimension-1) {
            neighbors.add(this.cloneExchanging(this.zeroI, this.zeroI, this.zeroJ, this.zeroJ+1));
        }
        return neighbors;
    }

    private Board cloneExchanging(final int originI, final int destI, final int originJ, final int destJ) {
        final int[][] newBlocks = new int[this.dimension][this.dimension];
        final int originValue = this.blocks[originI][originJ];
        final int destValue = this.blocks[destI][destJ];
        for (int i = 0; i < this.dimension; ++i) {
            newBlocks[i] = new int[this.dimension];
            for (int j = 0; j < this.dimension; ++j) {
                if (i == originI && j == originJ) {
                    newBlocks[i][j] = destValue;
                } else if (i == destI && j == destJ) {
                    newBlocks[i][j] = originValue;
                } else {
                    newBlocks[i][j] = this.blocks[i][j];
                }
            }
        }
        return new Board(newBlocks);
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        final StringBuilder s = new StringBuilder();
        s.append(this.dimension).append('\n');
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                s.append(String.format("%2d ", this.blocks[i][j]));
            }
            s.append('\n');
        }
        return s.toString();
    }
}
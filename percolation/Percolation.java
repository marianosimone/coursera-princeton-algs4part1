public class Percolation {

    private final boolean[] data;
    private final int n;
    private final WeightedQuickUnionUF quickFind;
    private final WeightedQuickUnionUF fullCheckFind;

    /**
    * create N-by-N grid, with all sites blocked
    **/
    public Percolation(final int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = N;
        this.data = new boolean[N*N];
        this.quickFind = new WeightedQuickUnionUF((N*N)+2);
        this.fullCheckFind = new WeightedQuickUnionUF((N*N)+1);
    }

    /**
    * open site (row i, column j) if it is not already
    **/
    public void open(final int i, final int j) {
        assertIndex(i, j);
        final int position = this.position(i, j);
        this.data[position] = true;
        if (i == 1) {
            this.quickFind.union(0, position+1);
            this.fullCheckFind.union(0, position+1);
        }
        if (i == this.n) {
            this.quickFind.union((n*n)+1, position+1);
        }
        for (final int p: this.openNeighbours(i, j)) {
            if (p != -1) {
                this.quickFind.union(position+1, p+1);
                this.fullCheckFind.union(position+1, p+1);
            }
        }
    }

    private int[] openNeighbours(final int i, final int j) {
        final int[] neighbours = {-1, -1, -1, -1};
        if (i > 1 && this.isOpen(i-1, j)) {
            neighbours[0] = position(i-1, j);
        }
        if (i < this.n && isOpen(i+1, j)) {
            neighbours[1] = position(i+1, j);
        }
        if (j > 1 && isOpen(i, j-1)) {
            neighbours[2] = position(i, j-1);
        }
        if (j < this.n && isOpen(i, j+1)) {
            neighbours[3] = position(i, j+1);
        }
        return neighbours;
    }

    /**
    * is site (row i, column j) open?
    **/
    public boolean isOpen(final int i, final int j) {
        assertIndex(i, j);
        return this.data[position(i, j)];
    }

    /**
    * is site (row i, column j) full?
    **/
    public boolean isFull(final int i, final int j) {
        assertIndex(i, j);
        return this.fullCheckFind.connected(position(i, j)+1, 0);
    }

    /**
    * does the system percolate?
    **/
    public boolean percolates() {
        return this.quickFind.connected(0, (n*n)+1);
    }

    private int position(final int i, final int j) {
        return  ((i-1)*n)+(j-1);
    }

    private void assertIndex(final int i, final int j) {
        if (i < 1 || i > this.n || j < 1 || j > this.n) {
            throw new IndexOutOfBoundsException();
        }
    }
}

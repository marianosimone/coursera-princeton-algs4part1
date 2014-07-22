public class PercolationStats {
    private final int t;
    private final double[] thresholds;
    private final double mean;
    private final double stddev;

    /**
    * Perform T independent computational experiments on an N-by-N grid
    **/
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.t = T;
        this.thresholds = new double[this.t];
        final double size = N*N;
        for (int it = 0; it < this.t; ++it) {
            final Percolation percolation = new Percolation(N);
            int iterations = 0;
            while (true) {
                final int i = StdRandom.uniform(1, N+1);
                final int j = StdRandom.uniform(1, N+1);

                if (!percolation.isOpen(i, j)) {
                    iterations += 1;
                    percolation.open(i, j);
                    if (percolation.percolates()) {
                        break;
                    }
                }
            }
            this.thresholds[it] = iterations/size;
        }
        this.mean = StdStats.mean(this.thresholds);
        this.stddev = StdStats.stddev(this.thresholds);
    }

    /**
    * sample mean of percolation threshold
    */
    public double mean()  {
        return this.mean;
    }

    /**
    * sample standard deviation of percolation threshold
    */
    public double stddev() {
        return this.stddev;
    }

    /**
    * returns lower bound of the 95% confidence interval
    */
    public double confidenceLo() {
        return this.mean - ((1.96*this.stddev)/Math.sqrt(this.t));
    }

    /**
    * returns upper bound of the 95% confidence interval
    */
    public double confidenceHi() {
        return this.mean + ((1.96*this.stddev)/Math.sqrt(this.t));
    }
 
    public static void main(String[] args) {
        final int N = Integer.valueOf(args[0]);
        final int T = Integer.valueOf(args[1]);
        final PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean                    = %.16f\n", stats.mean());
        StdOut.printf("stddev                  = %.16f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = %.16f, %.16f\n", stats.confidenceLo(), stats.confidenceHi());
    }
}

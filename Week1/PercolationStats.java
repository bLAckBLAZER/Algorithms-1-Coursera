/* *****************************************************************************
 *  Name: Omkar Jadhav
 *  Date: 24/05/2020
 *  Description: PercolationStats
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private int c = 0;
    private final int trials;
    private final double CONFIDENCE_95 = 1.96;
    private double thMean = -1.0;
    private double thStd = -1.0;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;


        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid arguments provided!");
        }
        else {
            thresholds = new double[trials];

            for (int i = 0; i < trials; i++) {
                Percolation oj = new Percolation(n);
                while (!oj.percolates()) {
                    int x = StdRandom.uniform(1, n + 1);
                    int y = StdRandom.uniform(1, n + 1);

                    if (!oj.isOpen(x, y)) {
                        oj.open(x, y);
                    }


                }
                double ratio = ((double) oj.numberOfOpenSites() / (n * n));
                thresholds[c] = ratio;
                c++;


            }
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        this.thMean = StdStats.mean(thresholds);
        return this.thMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        this.thStd = StdStats.stddev(thresholds);
        return this.thStd;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        if (this.thMean == -1.0 || this.thStd == -1.0) {
            return (mean() - ((stddev() * CONFIDENCE_95) / Math.sqrt(this.trials)));
        }
        else {
            return (this.thMean - ((this.thStd * CONFIDENCE_95) / Math.sqrt(this.trials)));
        }
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        if (this.thMean == -1.0 || this.thStd == -1.0) {
            return (mean() + ((stddev() * CONFIDENCE_95) / Math.sqrt(this.trials)));
        }
        else {
            return (this.thMean + ((this.thStd * CONFIDENCE_95) / Math.sqrt(this.trials)));
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

            PercolationStats statsobj = new PercolationStats(n, trials);


            System.out.println("mean: " + statsobj.mean());
            System.out.println("stddev: " + statsobj.stddev());
            System.out
                    .println("95% confidence interval: " + statsobj.confidenceLo() + ", " + statsobj
                            .confidenceHi());

        }
        else {
            System.out.println("Arguments not provided!");
        }
    }
}

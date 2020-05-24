/* *****************************************************************************
 *  Name: Omkar Jadhav
 *  Date: 24/05/2020
 *  Description: Percolation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private boolean[][] grid;
    private final WeightedQuickUnionUF obj;
    private int osCount = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();

        }
        // create a n*n grid and a o to n^2-1 array
        grid = new boolean[n + 1][n + 1]; // indexing is from 1
        // System.out.println("Grid Length: " + grid.length);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                grid[i][j] = false;

            }

        }

        // +2 for virtual nodes
        obj = new WeightedQuickUnionUF(n * n + 2);

        // // join virtual nodes to top and bottom nodes
        // for (int i = 1; i <= n; i++) {
        //     obj.union(0, i);
        // }
        // for (int i = n * n - n + 1; i <= n * n; i++) {
        //     obj.union(n * n + 1, i);
        // }


    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > grid.length - 1 || col <= 0 || col > grid.length - 1) {
            throw new IllegalArgumentException();

        }
        int n = grid.length - 1;
        int pos = n * row - (n - col);
        if (!grid[row][col]) {
            grid[row][col] = true;
            osCount++;
            // connect right,left,up and down nodes

            if (pos + 1 > 0 && pos + 1 < n * n + 1 && col + 1 <= n) {
                if (grid[row][col + 1]) {
                    obj.union(pos, pos + 1);
                }
            }
            if (pos - 1 > 0 && pos - 1 < n * n + 1 && col - 1 >= 1) {
                if (grid[row][col - 1]) {
                    obj.union(pos, pos - 1);
                }

            }
            if (pos - n > 0 && pos - n < n * n + 1 && row - 1 >= 0) {
                if (grid[row - 1][col]) {
                    obj.union(pos, pos - n);
                }

            }
            if (pos + n > 0 && pos + n < n * n + 1 && row + 1 <= n) {
                if (grid[row + 1][col]) {
                    obj.union(pos, pos + n);
                }

            }

        }

        // If the grid[row][col] is a node on first row and opened, create a union with VirtualTopNode
        if (row == 1 && grid[row][col]) {
            obj.union(pos, 0);
        }
        if (row == n && grid[row][col]) {
            obj.union(pos, n * n + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > grid.length - 1 || col <= 0 || col > grid.length - 1) {
            throw new IllegalArgumentException();

        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > grid.length - 1 || col <= 0 || col > grid.length - 1) {
            throw new IllegalArgumentException();

        }
        int n = grid.length - 1;
        // return obj.connected(n * row - (n - col), 0);
        return (obj.find(n * row - (n - col)) == obj.find(0));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return osCount;

    }

    // does the system percolate?
    public boolean percolates() {
        int n = grid.length - 1;
        // return obj.connected(0, n * n + 1);
        return (obj.find(0) == obj.find(n * n + 1));
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int x = StdRandom.uniform(1, n + 1);
            int y = StdRandom.uniform(1, n + 1);

            if (!p.isOpen(x, y)) {
                p.open(x, y);
            }


        }
        System.out.println("\nOpen Sites is " + (p.numberOfOpenSites()));

        System.out.println("\nN square is " + (n * n));
        double ratio = ((double) p.numberOfOpenSites() / (n * n));
        System.out.println("\nRatio is " + ratio);

        //
        // // Final grid which can percolate
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1; j <= n; j++) {
        //         System.out.print(p.grid[i][j] + " ");
        //
        //     }
        //     System.out.println();
        // }

        // for (int i = 0; i < n; i++) {
        //     int x = StdIn.readInt();
        //     int y = StdIn.readInt();
        //     p.open(x, y);
        //     System.out.println(p.grid[x][y]);
        //
        //
        // }

    }

}


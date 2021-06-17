import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int[][] grid;
    private final WeightedQuickUnionUF quickUnionUF;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private int count;

    public Percolation(int n) {
        virtualBottomSite = n * n + 1;
        virtualTopSite = 0;
        quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        grid = new int[n][n]; // n Zeile, n Spalten oder n Array mit jeweils n Einträg
    }

    public int to1D(int row, int col) {
        return (row * grid.length + col) + 1;
    }

    public void open(int row, int col) {
        --row;
        --col;
        if (row >= grid.length || col >= grid.length || row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
        else {
            if (!isOpen(row, col)) {
                grid[row][col] = 1;
                ++count;
                if (row == 0) {
                    quickUnionUF.union(virtualTopSite, to1D(row, col));
                }
                if (row == grid.length - 1) {
                    quickUnionUF.union(virtualBottomSite, to1D(row, col));
                }
            }
            if (row - 1 >= 0 && isOpen(row - 1, col)) {
                quickUnionUF.union(to1D(row, col), to1D(row - 1, col));
            }
            if (row + 1 < grid.length && isOpen(row + 1, col)) {
                quickUnionUF.union(to1D(row, col), to1D(row + 1, col));
            }
            if (col + 1 < grid.length && isOpen(row, col + 1)) {
                quickUnionUF.union(to1D(row, col), to1D(row, col + 1));
            }
            if (col - 1 >= 0 && isOpen(row, col - 1)) {
                quickUnionUF.union(to1D(row, col), to1D(row, col - 1));
            }

        }
    }

    public boolean isOpen(int row, int col) {
        if (row >= grid.length || col >= grid.length || row < 0 || col < 0)
            throw new IllegalArgumentException();

        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        --row;
        --col;

        if (row >= grid.length || col >= grid.length || row < 0 || col < 0)
            throw new IllegalArgumentException();

        return quickUnionUF.find(to1D(row,col)) == quickUnionUF.find(virtualTopSite)
                && isOpen(row,col);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return (quickUnionUF.find(virtualTopSite) == quickUnionUF.find(virtualBottomSite));
    }
}
package maxProductMatrix;

public class maxProductMatrix {

    static int MOD = 100000007;

    public static void main(String[] args) {

        int[][] grid = {{-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}};
        int n = grid.length;
        int m = grid[0].length;
        long product = 1;

        System.out.println(maxProductRec(n - 1, m - 1, grid));
    }

    public static int maxProductRec(int i, int j, int grid[][]) {

        if (i < 0 || j < 0) return (int) 1e9;

        if (i == 0 && j == 0) return grid[i][j];

        int up = grid[i - 1][j] * maxProductRec(i - 1, j, grid);

        int left = grid[i][j - 1] * maxProductRec(i, j - 1, grid);
        return Math.max(up, left);
    }
}

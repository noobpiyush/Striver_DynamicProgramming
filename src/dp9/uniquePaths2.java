package dp9;

import java.util.Arrays;

public class uniquePaths2 {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0},
                {0, -1, 0},
                {0, 0, 0}
        };

        int m = maze.length;

        int n =  maze[0].length;

        System.out.println(countPathsR(maze));

        System.out.println(countPathsMemo(maze));

        System.out.println(countPathTabular(maze));

        System.out.println(countPathsSpaceOptimization(m,n,maze));

    }

    public static int countPathsR(int[][] obstacleGrid) {
        int m = obstacleGrid.length;

        int n = obstacleGrid[0].length;

        return countPathsRHelper(m - 1, n - 1, obstacleGrid);
    }

    public static int countPathsRHelper(int i, int j, int[][] obstacleGrid) {

        if (i > 0 && j > 0 && obstacleGrid[i][j] == -1) return 0;

        if (i == 0 && j == 0) {

            return 1;
        }

        if (i < 0 || j < 0) return 0;

        int up = countPathsRHelper(i - 1, j, obstacleGrid);

        int left = countPathsRHelper(i, j - 1, obstacleGrid);

        return up + left;

    }

    public static int countPathsMemo(int[][] obstacleGrid) {
        int m = obstacleGrid.length;

        int n = obstacleGrid[0].length;

        int dp[][] = new int[m][n];
        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return countPathsMemoHelper(m - 1, n - 1, obstacleGrid, dp);
    }

    public static int countPathsMemoHelper(int i, int j, int[][] obstacleGrid, int dp[][]) {


        if (i > 0 && j > 0 && obstacleGrid[i][j] == -1) return 0;

        if (i < 0 || j < 0) {
            return 0;
        }

        if (i == 0 && j == 0) {

            return 1;
        }

        if (dp[i][j] != -1) return dp[i][j];


        int up = countPathsMemoHelper(i - 1, j, obstacleGrid, dp);

        int left = countPathsMemoHelper(i, j - 1, obstacleGrid, dp);

        return dp[i][j] = up + left;

    }

    public static int countPathTabular(int[][] obstacleGrid) {
        int m = obstacleGrid.length;

        int n = obstacleGrid[0].length;

        int dp[][] = new int[m][n];
        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return countPathsTabulationHelper(m, n, obstacleGrid, dp);
    }

    public static int countPathsTabulationHelper(int m, int n, int[][] obstacleGrid, int dp[][]) {


        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (i > 0 && j > 0 && obstacleGrid[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                int up = 0;
                int left = 0;

                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }

                dp[i][j] = up + left;
            }
        }


        return dp[m - 1][n - 1];

    }

    public static int countPathsSpaceOptimization(int m, int n, int[][] obstacleGrid) {

        int prev[] = new int[m];

        for (int i = 0; i < m; i++) {
            int temp[] = new int[n];
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    temp[j] = 1;
                    continue;
                }
                if (i > 0 && j > 0 && obstacleGrid[i][j] == -1) {
                    temp[j] = 0;
                    continue;
                }

                int up = 0;
                int left = 0;

                if (i > 0) {
                    up = prev[j];
                }
                if (j > 0) {
                    left = temp[j - 1];
                }

               temp[j] = up + left;
            }
            prev = temp;
        }


        return prev[n-1];

    }

}

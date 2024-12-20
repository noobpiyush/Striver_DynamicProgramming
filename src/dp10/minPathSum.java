package dp10;

import java.util.Arrays;

public class minPathSum {

    public static void main(String[] args) {
        int matrix[][] = {
                {5, 9, 6},
                {11, 5, 2}
        };

        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m]; // Corrected dimensions

        System.out.println(minPathSumR(matrix));
        System.out.println(Memo(matrix, n - 1, m - 1, dp));

        System.out.println(minPathSumTabular(matrix, n, m));
        System.out.println(minPathSumSpaceOptmization(matrix,n,m));
    }

    public static int minPathSumR(int mat[][]) {
        int m = mat.length;
        int n = mat[0].length;
        return minPathSumRHelper(mat, m - 1, n - 1);
    }

    public static int minPathSumRHelper(int mat[][], int m, int n) {
        if (m == 0 && n == 0) {
            return mat[0][0];
        }

        if (m < 0 || n < 0) {
            return (int) Math.pow(10, 9);
        }

        int up = mat[m][n] + minPathSumRHelper(mat, m - 1, n);
        int left = mat[m][n] + minPathSumRHelper(mat, m, n - 1);

        return Math.min(up, left);
    }

    public static int minPathSumMemoHelper(int mat[][], int m, int n, int dp[][]) {
        if (m == 0 && n == 0) {
            return mat[0][0];
        }

        if (m < 0 || n < 0) {
            return (int) Math.pow(10, 9);
        }

        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        int up = mat[m][n] + minPathSumMemoHelper(mat, m - 1, n, dp);
        int left = mat[m][n] + minPathSumMemoHelper(mat, m, n - 1, dp);

        return dp[m][n] = Math.min(up, left);
    }

    public static int minPathSumTabular(int mat[][], int m, int n) {

        int dp[][] = new int[m][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    dp[i][j] = mat[i][j];
                } else {
                    int up = mat[i][j];
                    if (i > 0) {
                        up += dp[i - 1][j];
                    } else {
                        up += (int) Math.pow(10, 9);
                    }

                    int left = mat[i][j];

                    if (j > 0) {

                        left += dp[i][j - 1];
                    } else {
                        left = (int) Math.pow(10, 9);
                    }

                    dp[i][j] = Math.min(up, left);
                }
            }
        }


        return dp[m - 1][n - 1];
    }

    public static int minPathSumSpaceOptmization(int mat[][], int m, int n) {

        int prev[] = new int[n];

        for (int i = 0; i < m; i++) {
            int temp[] = new int[n];
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    temp[j] = mat[i][j];
                } else {
                    int up = mat[i][j];
                    if (i > 0) {
                        up += prev[j];
                    } else {
                        up += (int) Math.pow(10, 9);
                    }

                    int left = mat[i][j];
                    if (j > 0) {
                        left += temp[j - 1];
                    } else {
                        left += (int) Math.pow(10, 9);  // Corrected from '=' to '+='
                    }

                    temp[j] = Math.min(up, left);
                }
            }
            prev = temp;
        }

        return prev[n - 1];  // Corrected from 'prev[m - 1]' to 'prev[n - 1]'
    }



    public static int Memo(int mat[][], int m, int n, int dp[][]) {
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return minPathSumMemoHelper(mat, m, n, dp);
    }
}

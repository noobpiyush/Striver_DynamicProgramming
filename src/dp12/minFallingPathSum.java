package dp12;

import java.util.*;

public class minFallingPathSum {

    static int Max = 100000;

    public static void main(String[] args) {
        int matrix[][] = {{2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}};

        System.out.println(pathSum(matrix));
        System.out.println(pathSumMemo(matrix));

        int n = matrix.length;

        int m = matrix[0].length;

        System.out.println(pathSumMTabularUtil(matrix, n, m));

        System.out.println(pathSumSpace(matrix, n, m));
    }

    public static int pathSumUtil(int mat[][], int i, int j, int n, int m) {

        if (j < 0 || j >= m) {
            return Max;
        }

        if (i == 0) {
            return mat[0][j];
        }

        //psbl paths exploration

        int up = mat[i][j] + pathSumUtil(mat, i - 1, j, n, m);

        int leftDiagonal = mat[i][j] + pathSumUtil(mat, i - 1, j - 1, n, m);

        int rightDiagnonal = mat[i][j] + pathSumUtil(mat, i - 1, j + 1, n, m);

        return Math.min(up, Math.min(leftDiagonal, rightDiagnonal));

    }

    public static int pathSumMemoUtil(int mat[][], int i, int j, int n, int m, int dp[][]) {

        if (j < 0 || j >= m) {
            return Max;
        }

        if (i == 0) {
            return dp[0][j] = mat[0][j];
        }

        //psbl paths exploration
        if (dp[i][j] != -1) return dp[i][j];

        int up = mat[i][j] + pathSumMemoUtil(mat, i - 1, j, n, m, dp);

        int leftDiagonal = mat[i][j] + pathSumMemoUtil(mat, i - 1, j - 1, n, m, dp);

        int rightDiagnonal = mat[i][j] + pathSumMemoUtil(mat, i - 1, j + 1, n, m, dp);

        return dp[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagnonal));

    }

    public static int pathSumMTabularUtil(int mat[][], int n, int m) {
        int dp[][] = new int[n][m];

        for (int k = 0; k < m; k++) {
            dp[0][k] = mat[0][k];
        }

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < m; j++) {

                int up = mat[i][j] + dp[i - 1][j];

                int leftD = mat[i][j];

                if (j - 1 >= 0) {
                    leftD += dp[i - 1][j - 1];
                } else {
                    leftD += Max;
                }

                int rightD = mat[i][j];

                if (j + 1 < m) {
                    rightD += dp[i - 1][j + 1];
                } else {
                    rightD += Max;
                }

                dp[i][j] = Math.min(up, Math.min(leftD, rightD));
            }
        }

        //psbl paths exploration

        int maxi = Integer.MAX_VALUE;

        for (int j = 0; j < m; j++) {
            maxi = Math.min(dp[n - 1][j], maxi);

        }

        return maxi;

    }

    public static int pathSumSpace(int mat[][], int n, int m) {

        List<Integer> prev = new ArrayList<>(Collections.nCopies(m, 0));

        List<Integer> cur = new ArrayList<>(Collections.nCopies(m, 0));

        for (int k = 0; k < m; k++) {
            prev.set(k, mat[0][k]);
        }

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < m; j++) {

                int up = mat[i][j] + prev.get(j);

                int leftD = mat[i][j];

                if (j - 1 >= 0) {
                    leftD += prev.get(j - 1);
                } else {
                    leftD += Max;
                }

                int rightD = mat[i][j];

                if (j + 1 < m) {
                    rightD += prev.get(j + 1);
                } else {
                    rightD += Max;
                }

                cur.set(j, Math.min(up, Math.min(leftD, rightD)));
            }

            prev = new ArrayList<>(cur);
        }

        //psbl paths exploration

        int maxi = Integer.MAX_VALUE;

        for (int j = 0; j < m; j++) {
            maxi = Math.min(prev.get(j), maxi);

        }

        return maxi;

    }

    public static int pathSum(int mat[][]) {
        int n = mat.length;
        int m = mat[0].length;
        int min = Integer.MAX_VALUE;


        for (int j = 0; j < m; j++) {
            int min2 = pathSumUtil(mat, n - 1, j, n, m);
            min = Math.min(min, min2);
        }

        return min;
    }

    public static int pathSumMemo(int mat[][]) {
        int n = mat.length;
        int m = mat[0].length;
        int min = Integer.MAX_VALUE;

        int dp[][] = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);


        for (int j = 0; j < m; j++) {
            int min2 = pathSumUtil(mat, n - 1, j, n, m);
            min = Math.min(min, min2);
        }

        return min;
    }

}

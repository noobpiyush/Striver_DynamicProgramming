package dp11;

import java.util.Arrays;

public class triangle {

    public static void main(String[] args) {
        int triangle1[][] = {{1},
                {2, 3},
                {3, 6, 7},
                {8, 9, 6, 10}};

        int n = triangle1.length;

        System.out.println(findPathSumRec(triangle1, n));

        System.out.println(findPathMemo(triangle1, n));

        System.out.println(findPathSumTabular(n, triangle1));

        System.out.println(findPathSumSpaceOPTM(n,triangle1));


    }

    public static int findPathSumRecHelper(int i, int j, int n, int mat[][]) {

        //base case

        if (i == n - 1) {
            return mat[i][j];
        }

        //computation 2 opt down or diag

        int down = mat[i][j] + findPathSumRecHelper(i + 1, j, n, mat);

        int diagonal = mat[i][j] + findPathSumRecHelper(i + 1, j + 1, n, mat);

        return Math.min(down, diagonal);
    }

    public static int findPathSumRec(int mat[][], int n) {

        return findPathSumRecHelper(0, 0, n, mat);
    }

    public static int findPathSumMemoUtil(int i, int j, int n, int mat[][], int dp[][]) {

        //base case

        if (dp[i][j] != -1) return dp[i][j];

        if (i == n - 1) {
            return dp[i][j] = mat[i][j];
        }

        //computation 2 opt down or diag

        int down = mat[i][j] + findPathSumMemoUtil(i + 1, j, n, mat, dp);

        int diagonal = mat[i][j] + findPathSumMemoUtil(i + 1, j + 1, n, mat, dp);

        return dp[i][j] = Math.min(down, diagonal);
    }

    public static int findPathMemo(int mat[][], int n) {

        int dp[][] = new int[n][n];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return findPathSumMemoUtil(0, 0, n, mat, dp);
    }

    public static int findPathSumTabular(int n, int mat[][]) {
        int dp[][] = new int[n][n];
        //base case
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = mat[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {

            for (int j = i; j >= 0; j--) {
                int up = mat[i][j] + dp[i + 1][j];

                int diagonal = mat[i][j] + dp[i + 1][j + 1];

                dp[i][j] = Math.min(up, diagonal);
            }
        }


        return dp[0][0];
    }

    public static int findPathSumSpaceOPTM(int n, int mat[][]) {
        int front[] = new int[n];
        int cur[] = new int[n];
        //base case
        for (int j = 0; j < n; j++) {
            front[j] = mat[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {

            for (int j = i; j >= 0; j--) {
                int up = mat[i][j] + front[j];


                int diagonal = mat[i][j] + front[j + 1];

                cur[j] = Math.min(up, diagonal);
            }
            front = cur.clone();
        }


        return front[0];
    }
}

package dp13_3d_Dp;

import java.util.Arrays;

public class cherryPickup2 {

    public static void main(String[] args) {
        int matrix[][] = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };
        System.out.println(maxSumRecursive(matrix));

        System.out.println(maxSumMemo(matrix));

        System.out.println(maxSumTabulation(matrix));
    }

    public static int maxSumRecursive(int mat[][]) {

        int n = mat.length;

        int m = mat[0].length;

        return maxSumRecursiveUtil(0, 0, m - 1, n, m, mat);

    }

    public static int maxSumRecursiveUtil(int i, int j1, int j2, int n, int m, int mat[][]) {

        //base case

        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) {
            return (int) -1e9;
        }


        if (i == n - 1) {
            if (j1 == j2) {
                return mat[n - 1][j1];
            } else {
                return mat[n - 1][j1] + mat[n - 1][j2];
            }
        }

        // process
        int max = Integer.MIN_VALUE;
        for (int di = -1; di <= 1; di++) {

            for (int dj = -1; dj <= 1; dj++) {
                int ans;

                if (j1 == j2) {
                    ans = mat[i][j1] + maxSumRecursiveUtil(i + 1, j1 + di, j2 + dj, n, m, mat);
                } else {
                    ans = mat[i][j1] + mat[i][j2] + maxSumRecursiveUtil(i + 1, j1 + di, j2 + dj, n, m, mat);
                }

                max = Math.max(max, ans);
            }
        }
        return max;

    }

    public static int maxSumMemo(int mat[][]) {

        int n = mat.length;

        int m = mat[0].length;

        int[][][] dp = new int[n][m][m];

        for (int row[][] : dp) {
            for (int row2[] : row) {
                Arrays.fill(row2, -1);
            }
        }

        return maxSumMemoUtil(0, 0, m - 1, n, m, mat, dp);

    }

    public static int maxSumMemoUtil(int i, int j1, int j2, int n, int m, int mat[][], int dp[][][]) {

        //base case

        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) {
            return (int) -1e9;
        }


        if (i == n - 1) {
            if (j1 == j2) {
                return mat[n - 1][j1];
            } else {
                return mat[n - 1][j1] + mat[n - 1][j2];
            }
        }

        if (dp[i][j1][j2] != -1) return dp[i][j2][j1];

        // process
        int max = Integer.MIN_VALUE;
        for (int di = -1; di <= 1; di++) {

            for (int dj = -1; dj <= 1; dj++) {
                int ans;

                if (j1 == j2) {
                    ans = mat[i][j1] + maxSumRecursiveUtil(i + 1, j1 + di, j2 + dj, n, m, mat);
                } else {
                    ans = mat[i][j1] + mat[i][j2] + maxSumRecursiveUtil(i + 1, j1 + di, j2 + dj, n, m, mat);
                }

                max = Math.max(max, ans);
            }
        }
        return dp[i][j1][j2] = max;
    }

    public static int maxSumTabulation(int mat[][]) {

        int n = mat.length;

        int m = mat[0].length;

        int[][][] dp = new int[n][m][m];

        for (int row[][] : dp) {
            for (int row2[] : row) {
                Arrays.fill(row2, -1);
            }
        }

        //base case we will start filling rom last row as in recursion our last step was at last  row


        for (int j1 = 0; j1 < m; j1++) {

            for (int j2 = 0; j2 < m; j2++) {

                if (j1 == j2) {
                    dp[n - 1][j1][j2] = mat[n - 1][j1];
                } else {
                    dp[n - 1][j1][j2] = mat[n - 1][j1] + mat[n - 1][j2];
                }

            }

        }

        for (int i = n - 2; i >= 0; i--) {

            for (int j1 = 0; j1 < m; j1++) {

                for (int j2 = 0; j2 < m; j2++) {
                    int max = Integer.MIN_VALUE;

                    for (int di =-1;di<=1;di++){

                        for (int dj =-1;dj<=1;dj++){
                            int ans;

                            if (j1 == j2){
                                ans = mat[i][j1];
                            }else{
                                ans = mat[i][j1] + mat[i][j2];
                            }

                            if (j1 + di >= m || j1 + di < 0 || j2 + dj >= m || j2 + dj <  0){
                                ans += (int) Math.pow(-10, 9);
                            }
                            else{
                                ans += dp[i+1][j1 + di][j2  + dj];
                            }

                            max = Math.max(max,ans);
                        }

                    }
                    dp[i][j1][j2] = max;
                }
            }
        }
        return dp[0][0][m-1];
    }

}
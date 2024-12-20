package dp33EditDist;

import java.util.Arrays;

public class EditDistance {

    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";
        int n = s1.length();
        int m = s2.length();

        System.out.println(minOpR(n - 1, m - 1, n, m, s1, s2));
        System.out.println(minOpMemo(n, m, s1, s2));
        System.out.println(minOpTab(n, m, s1, s2));
    }

    public static int minOpR(int i, int j, int n, int m, String s1, String s2) {

        //
        if (j < 0) return i + 1;

        if (i < 0) return j + 1;

        if (s1.charAt(i) == s2.charAt(j)) {
            return minOpR(i - 1, j - 1, n, m, s1, s2);
        } else {
            // replace del in
            return 1 + Math.min(minOpR(i - 1, j - 1, n, m, s1, s2), Math.min(minOpR(i - 1, j, n, m, s1, s2), minOpR(i, j - 1, n, m, s1, s2)));
        }
    }

    public static int minOpMemo(int n, int m, String s1, String s2) {

        int dp[][] = new int[n][m];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return minOpMemoUtil(n - 1, m - 1, n, m, s1, s2, dp);
    }

    public static int minOpMemoUtil(int i, int j, int n, int m, String s1, String s2, int dp[][]) {

        //
        if (j < 0) return i + 1;

        if (i < 0) return j + 1;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = minOpMemoUtil(i - 1, j - 1, n, m, s1, s2, dp);
        } else {
            // replace del in
            return dp[i][j] = 1 + Math.min(minOpMemoUtil(i - 1, j - 1, n, m, s1, s2, dp), Math.min(minOpMemoUtil(i - 1, j, n, m, s1, s2, dp), minOpMemoUtil(i, j - 1, n, m, s1, s2, dp)));
        }
    }

    public static int minOpTab(int n, int m, String s1, String s2) {
        int dp[][] = new int[n+1][m+1];

        for (int i = 0;i<=n;i++){
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i =1;i<=n;i++){
            for (int j =1;j<=m;j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                     dp[i][j] = dp[i-1][j-1];
                } else {
                    // replace del in
                     dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }

       return dp[n][m];
    }
}

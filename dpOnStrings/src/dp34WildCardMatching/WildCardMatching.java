package dp34WildCardMatching;

import java.util.Arrays;

public class WildCardMatching {

    public static void main(String[] args) {
        String S1 = "ab*cd";
        String S2 = "abdefcd";

        int n = S1.length();
        int m = S2.length();

        if (wildCardMatchingRec(n - 1, m - 1, S1, S2) == 1)
            System.out.println("String S1 and S2 do match");
        else
            System.out.println("String S1 and S2 do not match");
        if (wildCardMatchingMemo(n, m, S1, S2) == 1)
            System.out.println("String S1 and S2 do match");
        else
            System.out.println("String S1 and S2 do not match");
        if (wildCardMatchingTabulation(n, m, S1, S2) == 1)
            System.out.println("String S1 and S2 do match");
        else
            System.out.println("String S1 and S2 do not match");
    }


    public static int wildCardMatchingRec(int i, int j, String pattern, String s1) {

        if (i < 0 && j < 0) {
            return 1;
        }
        if (i < 0 && j >= 0) {
            return 0;
        }

        if (i >= 0 && j < 0) {
            return isAllStars(pattern, 1) ? 1 : 0;
        }

        if (pattern.charAt(i) == s1.charAt(j) || pattern.charAt(i) == '?') {
            return wildCardMatchingRec(i - 1, j - 1, pattern, s1);
        } else {
            if (pattern.charAt(i) == '*') {
                return (wildCardMatchingRec(i - 1, j, pattern, s1) == 1 || wildCardMatchingRec(i, j - 1, pattern, s1) == 1) ? 1 : 0;
            } else {
                return 0;
            }
        }
    }

    public static int wildCardMatchingMemo(int n, int m, String pattern, String s1) {

        int dp[][] = new int[n][m];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return wildCardMatchingMemoUtil(n - 1, m - 1, pattern, s1, dp);
    }

    private static int wildCardMatchingMemoUtil(int i, int j, String pattern, String s1, int[][] dp) {
        if (i < 0 && j < 0) {
            return 1;
        }
        if (i < 0 && j >= 0) {
            return 0;
        }

        if (i >= 0 && j < 0) {
            return isAllStars(pattern, 1) ? 1 : 0;
        }

        if (dp[i][j] != -1) return dp[i][j];

        if (pattern.charAt(i) == s1.charAt(j) || pattern.charAt(i) == '?') {
            return dp[i][j] = wildCardMatchingMemoUtil(i - 1, j - 1, pattern, s1, dp);
        } else {
            if (pattern.charAt(i) == '*') {
                return dp[i][j] = (wildCardMatchingMemoUtil(i - 1, j, pattern, s1, dp) == 1 || wildCardMatchingMemoUtil(i, j - 1, pattern, s1, dp) == 1) ? 1 : 0;
            } else {
                return dp[i][j] = 0;
            }
        }
    }

    private static int wildCardMatchingTabulation(int n, int m, String pattern, String s1) {
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;

//        for (int i =1;i<=m;i++){
//            dp[0][i] = 0;
//        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = isAllStars(pattern, 1) ? 1 : 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (pattern.charAt(i-1) == s1.charAt(j-1) || pattern.charAt(i-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    if (pattern.charAt(i-1) == '*') {
                        dp[i][j] = (dp[i-1][j] == 1 || dp[i][j-1] == 1) ? 1 : 0;
                    } else {
                        dp[i][j] = 0;
                    }
                }

            }
        }
        return dp[n][m];
    }

    private static boolean isAllStars(String pattern, int i) {
        for (int j = 0; j <= i; j++) {
            if (pattern.charAt(j) != '*') {
                return false;
            }
        }
        return true;
    }
}

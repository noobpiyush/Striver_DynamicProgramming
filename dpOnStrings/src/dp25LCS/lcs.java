package dp25LCS;

import java.util.Arrays;

public class lcs {

    public static void main(String[] args) {
        String s1 = "acd";
        String s2 = "ced";

        int n1 = s1.length();

        int n2 = s2.length();

        System.out.println(LCS_Rec(n1 - 1, n2 - 1, s1, s2));

        System.out.println(LCS_Memo(n1, n2, s1, s2));

        System.out.println(LCS_Tabulation(s1, s2));

        System.out.println(LCS_Space(s1,s2));
    }

    public static int LCS_Rec(int index1, int index2, String s1, String s2) {
        //
        if (index1 < 0 || index2 < 0) {
            return 0;
        }

        if (s1.charAt(index1) == s2.charAt(index2)) {
            return 1 + LCS_Rec(index1 - 1, index2 - 1, s1, s2);
        } else {
            return Math.max(LCS_Rec(index1 - 1, index2, s1, s2), LCS_Rec(index1, index2 - 1, s1, s2));
        }
    }

    public static int LCS_Memo(int n1, int n2, String s1, String s2) {

        int dp[][] = new int[n1][n2];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return LCS_MemoUtil(n1 - 1, n2 - 1, s1, s2, dp);
    }

    public static int LCS_MemoUtil(int index1, int index2, String s1, String s2, int dp[][]) {
        //
        if (index1 < 0 || index2 < 0) {
            return 0;
        }

        if (dp[index1][index2] != -1) return dp[index1][index2];

        if (s1.charAt(index1) == s2.charAt(index2)) {
            return dp[index1][index2] = 1 + LCS_MemoUtil(index1 - 1, index2 - 1, s1, s2, dp);
        } else {
            return dp[index1][index2] = Math.max(LCS_MemoUtil(index1 - 1, index2, s1, s2, dp), LCS_MemoUtil(index1, index2 - 1, s1, s2, dp));
        }
    }

    public static int LCS_Tabulation(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];
        //

        for (int rows[] : dp)
            Arrays.fill(rows, -1);

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {

            for (int ind2 = 1; ind2 <= m; ind2++) {

                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else {
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

        }
        return dp[n][m];
    }

    public static int LCS_Space(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int prev[] = new int[m + 1];
        int cur[] = new int[m + 1];
        //

        for (int i = 0; i <= m; i++) {
            prev[i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {

            for (int ind2 = 1; ind2 <= m; ind2++) {

                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    cur[ind2] = 1 + prev[ind2-1];
                } else {
                    cur[ind2] = Math.max(prev[ind2], cur[ind2 - 1]);
                }
            }
            prev = (int[]) (cur.clone());
        }
        return prev[m];
    }

}

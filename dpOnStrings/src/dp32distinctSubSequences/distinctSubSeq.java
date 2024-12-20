package dp32distinctSubSequences;

import java.util.Arrays;

public class distinctSubSeq {
    static int prime = (int) (Math.pow(10, 9) + 7);

    public static void main(String[] args) {
        String s1 = "babgbag";
        String s2 = "bag";
        int n = s1.length();
        int m = s2.length();
        System.out.println(dSRec(n - 1, m - 1, n, m, s1, s2));
        System.out.println(dSMemo(n, m, s1, s2));
        System.out.println(dSTabulation(s1,s2));
//        System.out.println(dSSpaceOPTM(s1,s2));
    }

    public static int dSRec(int i, int j, int n, int m, String s1, String s2) {

        if (j < 0) return 1;
        if (i < 0) return 0;

        if (s1.charAt(i) == s2.charAt(j)) {
            int leave = dSRec(i - 1, j - 1, n, m, s1, s2);
            int stay = dSRec(i - 1, j, n, m, s1, s2);

            return (leave + stay) % prime;
        }

        return dSRec(i - 1, j, n, m, s1, s2);

    }

    public static int dSMemo(int n, int m, String s1, String s2) {
        int dp[][] = new int[n][m];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return dSMemoUtil(n - 1, m - 1, s1, s2, dp);
    }

    public static int dSMemoUtil(int i, int j, String s1, String s2, int dp[][]) {

        if (j < 0) return 1;

        if (i < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            int leave = dSMemoUtil(i - 1, j - 1, s1, s2, dp);
            int stay = dSMemoUtil(i - 1, j, s1, s2, dp);

            return dp[i][j] = (leave + stay) % prime;
        }

        return dp[i][j] = dSMemoUtil(i - 1, j, s1, s2, dp);

    }

    public static int dSTabulation(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n+1][m+1];

        for (int i =0;i<=n;i++){
            dp[i][0] = 1;
        }

        for (int i = 1;i<=n;i++){
            for (int j =1;j<=m;j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    int leave = dp[i-1][j-1];
                    int stay =dp[i-1][j];

                    dp[i][j] = (leave + stay) % prime;
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][m] ;
    }

//    public static int dSSpaceOPTM(String s1, String s2) {
//        int n = s1.length();
//        int m = s2.length();
//        int prev[] = new int[m + 1];
//        int cur[] = new int[m + 1];
//        for (int i = 0; i <= n; i++) {
//            prev[i] = 1;
//        }
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
//                    int leave = prev[j-1];
//                    int stay = prev[j];
//
//                    cur[j] = (leave + stay) % prime;
//                } else {
//                    cur[j] = prev[j];
//                }
//            }
//        }
//        return prev[m];
//    }

}

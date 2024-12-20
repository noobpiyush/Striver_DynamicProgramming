package dp24_RodCutting;

import java.util.Arrays;

public class RodCutting {

    public static void main(String[] args) {

        int N = 8;
        int Price[] = {1, 5, 8, 9, 10, 17, 17, 20};

        System.out.println(cutRodRec(Price, N, N - 1));

        System.out.println(cutRodMemo(Price, N));

        System.out.println(cutRodTab(Price, N));

        System.out.println(cutRodSpace(Price, N));
    }

    public static int cutRodRec(int price[], int n, int index) {

        if (index == 0) {
            return n * price[0];
        }

        int notTake = cutRodRec(price, n, index - 1);

        int rodLength = index + 1;

        int take = Integer.MIN_VALUE;

        if (rodLength <= n) {

            take = price[index] + cutRodRec(price, n - rodLength, index);
        }

        return Math.max(take, notTake);
    }

    public static int cutRodMemo(int price[], int n) {
        int dp[][] = new int[n][n + 1];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }
        return cutRodMemoUtil(price, n, n - 1, dp);
    }

    public static int cutRodMemoUtil(int price[], int n, int index, int dp[][]) {

        if (index == 0) {
            return n * price[0];
        }

        if (dp[index][n] != -1) return dp[index][n];

        int notTake = cutRodMemoUtil(price, n, index - 1, dp);

        int rodLength = index + 1;

        int take = Integer.MIN_VALUE;

        if (rodLength <= n) {

            take = price[index] + cutRodMemoUtil(price, n - rodLength, index, dp);
        }

        return dp[index][n] = Math.max(take, notTake);
    }

    public static int cutRodTab(int price[], int n) {

        int dp[][] = new int[n][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[0][i] = i * price[0];
        }

        for (int ind = 1; ind < n; ind++) {

            for (int len = 0; len <= n; len++) {

                int notTake = dp[ind - 1][len];

                int rodLength = ind + 1;

                int take = Integer.MIN_VALUE;

                if (rodLength <= len) {

                    take = price[ind] + dp[ind - 1][len - rodLength];
                }
                dp[ind][len] = Math.max(take, notTake);
            }

        }

        return dp[n - 1][n];
    }

    public static int cutRodSpace(int price[], int n) {

        int prev[] = new int[n + 1];
        int cur[] = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            prev[i] = i * price[0];
        }

        for (int ind = 1; ind < n; ind++) {

            for (int len = 0; len <= n; len++) {

                int notTake = prev[len];

                int rodLength = ind + 1;

                int take = Integer.MIN_VALUE;

                if (rodLength <= len) {

                    take = price[ind] + cur[len - rodLength];
                }
                cur[len] = Math.max(take, notTake);
            }
            prev = cur;
        }

        return prev[n];
    }
}

package dp23_unboundedKnapsack;

import java.util.Arrays;

public class unboundedKnapsack {

    public static void main(String[] args) {
        int wt[] = {2, 4, 6};
        int val[] = {5, 11, 13};
        int W = 10;

        int n = wt.length;

        System.out.println(knapsackR(n, W, n - 1, val, wt));

        System.out.println(knapsackMemo(n, W, val, wt));

        System.out.println(knapsackTab(n, W, val, wt));

        System.out.println(knapsackSpace1(n, W, val, wt));
    }

    public static int knapsackR(int N, int W, int index, int val[], int wt[]) {

        //base case

        if (index == 0) {
            return (W / wt[0]) * val[0];
        }

        int notTake = knapsackR(N, W, index - 1, val, wt);

        int take = Integer.MIN_VALUE;

        if (W >= wt[index]) {
            take = val[index] + knapsackR(N, W - wt[index], index, val, wt);
        }

        return Math.max(take, notTake);
    }

    public static int knapsackMemo(int N, int W, int val[], int wt[]) {

        int dp[][] = new int[N][W + 1];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }
        return knapsackMemoUtil(N, W, N - 1, val, wt, dp);
    }

    public static int knapsackMemoUtil(int N, int W, int index, int val[], int wt[], int dp[][]) {

        //base case

        if (index == 0) {
            return (W / wt[0]) * val[0];
        }

        if (dp[index][W] != -1) return dp[index][W];

        int notTake = knapsackR(N, W, index - 1, val, wt);

        int take = Integer.MIN_VALUE;

        if (W >= wt[index]) {
            take = val[index] + knapsackR(N, W - wt[index], index, val, wt);
        }

        return dp[index][W] = Math.max(take, notTake);
    }

    public static int knapsackTab(int N, int W, int val[], int wt[]) {

        //base case
        int dp[][] = new int[N][W + 1];

        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = ((int) i / wt[0]) * val[0];
        }

        for (int ind = 1; ind < N; ind++) {

            for (int cap = 0; cap <= W; cap++) {

                int notTake = dp[ind - 1][cap];

                int take = Integer.MIN_VALUE;

                if (cap >= wt[ind]) {
                    take = val[ind] + dp[ind][cap - wt[ind]];
                }

                dp[ind][cap] = Math.max(take, notTake);
            }

        }

        return dp[N - 1][W];
    }

    public static int knapsackSpace1(int N, int W, int val[], int wt[]) {

        //base case
        int prev[] = new int[W + 1];

        int cur[] = new int[W + 1];

        for (int i = wt[0]; i <= W; i++) {
            prev[i] = ((int) i / wt[0]) * val[0];
        }

        for (int ind = 1; ind < N; ind++) {

            for (int cap = 0; cap <= W; cap++) {

                int notTake = prev[cap];

                int take = Integer.MIN_VALUE;

                if (cap >= wt[ind]) {
                    take = val[ind] + cur[cap - wt[ind]];
                }

                cur[cap] = Math.max(take, notTake);
            }
            prev = cur;
        }

        return prev[W];
    }
}

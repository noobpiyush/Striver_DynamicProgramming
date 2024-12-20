package dp_20_coinchange1;

import java.util.Arrays;

public class coinChange1 {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3};
        int T = 7;
        int n = arr.length;

        System.out.println(minCoinsRec(arr, n, T, n - 1));

        System.out.println(minCoinsMemo(arr, n, T));

        System.out.println(minCoinsTabulation(arr, n, T));

        System.out.println(minCoinsSpaceOptimization(arr,n,T));
    }

    public static int minCoinsRec(int coins[], int n, int target, int index) {

        if (index == 0) {

            if (target % coins[0] == 0) {
                return target / coins[0];
            }

            return (int) Math.pow(10, 8);
        }
        //

        int notPick = minCoinsRec(coins, n, target, index - 1);

        int take = Integer.MAX_VALUE;

        if (target >= coins[index]) {
            take = 1 + minCoinsRec(coins, n, target - coins[index], index); //as infinite supply
        }
        return Math.min(take, notPick);
    }

    public static int minCoinsMemoUtil(int coins[], int n, int target, int index, int dp[][]) {

        if (index == 0) {

            if (target % coins[0] == 0) {
                return target / coins[0];
            }

            return (int) Math.pow(10, 9);
        }
        //

        if (dp[index][target] != -1) return dp[index][target];

        int notPick = minCoinsMemoUtil(coins, n, target, index - 1, dp);

        int take = Integer.MAX_VALUE;

        if (target >= coins[index]) {
            take = 1 + minCoinsMemoUtil(coins, n, target - coins[index], index, dp); //as infinite supply
        }
        return dp[index][target] = Math.min(take, notPick);
    }

    public static int minCoinsMemo(int coins[], int n, int target) {

        int dp[][] = new int[n][target + 1];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        int ans = minCoinsMemoUtil(coins, n, target, n - 1, dp);

        if (ans >= (int) Math.pow(10, 9)) {
            return -1;
        }
        return ans;
    }

    public static int minCoinsTabulation(int coins[], int n, int target) {

        int dp[][] = new int[n][target + 1];

        for (int i = 0; i <= target; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = (int) Math.pow(10, 9);
            }
        }

        for (int i = 1; i < n; i++) {

            for (int t = 0; t <= target; t++) {

                int notPick = dp[i - 1][t];

                int take = (int) Math.pow(10, 9);

                if (t >= coins[i]) {
                    take = 1 + dp[i][t - coins[i]]; //as infinite supply
                }
                dp[i][t] = Math.min(take, notPick);
            }
        }
        int ans = dp[n - 1][target];
        // If it's not possible to achieve the target sum, return -1
        if (ans >= (int) Math.pow(10, 9))
            return -1;
        return ans;

    }

    public static int minCoinsSpaceOptimization(int coins[], int n, int target) {

        int prev[] = new int[target + 1];
        int cur[] = new int[target + 1];
        for (int i = 0; i <= target; i++) {
            if (i % coins[0] == 0) {
                prev[i] = i / coins[0];
            } else {
                prev[i]  = (int) Math.pow(10, 9);
            }
        }

        for (int i = 1; i < n; i++) {

            for (int t = 0; t <= target; t++) {

                int notPick = prev[t];

                int take = (int) Math.pow(10, 9);

                if (t >= coins[i]) {
                    take = 1 + cur[t - coins[i]]; //as infinite supply
                }
                cur[t] = Math.min(take, notPick);
            }

            prev = cur.clone();
        }
        int ans = prev[target];

        // If it's not possible to achieve the target sum, return -1

        if (ans >= (int) Math.pow(10, 9))
            return -1;

        return ans;

    }
}

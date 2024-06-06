package dp2;

import java.util.Arrays;

public class climbingstairs {

    public static void main(String[] args) {
        int n = 5;
        int dp[] = new int[n + 1];

        Arrays.fill(dp, -1);

        System.out.println(climbStairs_Recursive(n));
        System.out.println(climbStairs_Memoization(n, dp));
        System.out.println(climbStairs_Tabular(n, dp));
        System.out.println(climbStairs_optimised(n));
    }

    public static int climbStairs_Recursive(int n) {
        if (n <= 1) {
            return 1;
        }

        int oneStep = climbStairs_Recursive(n - 1);

        int secondStep = climbStairs_Recursive(n - 2);

        return oneStep + secondStep;
    }

    public static int climbStairs_Memoization(int n, int dp[]) {
        if (n <= 1) {
            return 1;
        }
//        dp[1] = 1;
//        dp[0] = 1;

        if (dp[n] != -1) return dp[n];

        return dp[n] = climbStairs_Memoization(n - 1, dp) + climbStairs_Memoization(n - 2, dp);

    }

    public static int climbStairs_Tabular(int n, int dp[]) {

       dp[0] = 1;
       dp[1] = 1;

        for (int i = 2; i <= n; i++) {
           dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static int climbStairs_optimised(int n) {

        int prev = 1;
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            int cur = prev + prev2;
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}

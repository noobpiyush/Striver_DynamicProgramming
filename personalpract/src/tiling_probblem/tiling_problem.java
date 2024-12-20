package tiling_problem;

import java.util.Arrays;

public class tiling_problem {
    static int MOD = 1000000007; // Standard modulo for large numbers

    public static void main(String[] args) {
        int n = 4;
        System.out.println(tiling_Recursive(n)); // Recursive solution

        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(tiling_Memoization(n, dp)); // Memoization solution

        System.out.println(tiling_Tabular(n)); // Tabulation solution

        System.out.println(tiling_SpaceOpt(n)); // Space-optimized solution
    }

    // Recursive approach
    public static int tiling_Recursive(int n) {
        if (n <= 2) {
            return n;
        }
        return (tiling_Recursive(n - 1) + tiling_Recursive(n - 2)) % MOD;
    }

    // Memoization approach
    public static int tiling_Memoization(int n, int dp[]) {
        if (n <= 2) {
            return n;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = (tiling_Memoization(n - 1, dp) + tiling_Memoization(n - 2, dp)) % MOD;
        return dp[n];
    }

    // Tabulation approach
    public static int tiling_Tabular(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        return dp[n];
    }

    // Space-optimized approach
    public static int tiling_SpaceOpt(int n) {
        if (n <= 2) {
            return n;
        }
        int prev2 = 1;
        int prev = 2;
        for (int i = 3; i <= n; i++) {
            int cur = (prev + prev2) % MOD;
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}

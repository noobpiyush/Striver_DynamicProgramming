package dp3;

import java.util.Arrays;

public class frogJump {

    public static void main(String[] args) {

        int height[] = {30, 10, 60, 10, 60, 50};
        int n = height.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);

        System.out.println(frogJumpRecursive(height, n, n - 1));
        System.out.println(frogJumpMemoization(height, n, n - 1, dp));
        System.out.println(frogJumpTabular(height, n, dp));
        System.out.println(frogJumpOptimised(height, n));
    }


    public static int frogJumpRecursive(int heights[], int n, int index) {

        if (index == 0) {

            return 0;
        }

        int left = frogJumpRecursive(heights, n, index - 1) + Math.abs(heights[index] - heights[index - 1]);
        int right;
        if (index > 1) {
            right = frogJumpRecursive(heights, n, index - 2) + Math.abs(heights[index] - heights[index - 2]);
        } else {
            right = Integer.MAX_VALUE;
        }

        return Math.min(left, right);
    }

    public static int frogJumpMemoization(int heights[], int n, int index, int dp[]) {

        if (index == 0) {
            return 0;
        }

        if (dp[index] != -1) return dp[index];

        int left = frogJumpRecursive(heights, n, index - 1) + Math.abs(heights[index] - heights[index - 1]);
        int right;
        if (index > 1) {
            right = frogJumpRecursive(heights, n, index - 2) + Math.abs(heights[index] - heights[index - 2]);
        } else {
            right = Integer.MAX_VALUE;
        }

        return dp[index] = Math.min(left, right);
    }

    public static int frogJumpTabular(int[] heights, int n, int[] dp) {

        dp[0] = 0;
        int left, right;
        for (int i = 1; i < n; i++) {
            left = dp[i - 1] + Math.abs(heights[i - 1] - heights[i]);

            if (i > 1) {
                right = dp[i - 2] + Math.abs(heights[i - 2] - heights[i]);
            } else {
                right = Integer.MAX_VALUE;
            }

            dp[i] = Math.min(left, right);
        }

        return dp[n - 1];
    }

    public static int frogJumpOptimised(int[] heights, int n) {

        int prev = 0;
        int prev2 = 0;
        int left, right;
        for (int i = 1; i < n; i++) {
            left = prev + Math.abs(heights[i - 1] - heights[i]);

            if (i > 1) {
                right = prev2 + Math.abs(heights[i - 2] - heights[i]);
            } else {
                right = Integer.MAX_VALUE;
            }


            int cur = Math.min(left, right);
            prev2 = prev;
            prev = cur;
        }

        return prev;
    }
}

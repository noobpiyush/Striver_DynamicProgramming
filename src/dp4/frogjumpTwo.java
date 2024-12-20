package dp4;

import java.util.Arrays;

public class frogjumpTwo {

    public static void main(String[] args) {
        int height[] = {30, 10, 60, 10, 60, 50};
        int k = 2;
        int n = height.length;
        System.out.println(frogJumps_Recursive(n - 1, height, k));
        int dp[] = new int[n + 1];
        Arrays.fill(dp,-1);
        System.out.println(frogJumps_Memoization(n-1,height,dp,k));

    }

    public static int frogJumps_Recursive(int index, int heights[], int k) {
        if (index == 0) return 0;

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= k; i++) {
            if (index - i >= 0) {
                int jump = frogJumps_Recursive(index - i, heights, k) + Math.abs(heights[index] - heights[index - i]);
                min = Math.min(min, jump);
            }
        }

        return min;
    }

    public static int frogJumps_Memoization(int index, int heights[], int dp[], int k) {
        if (index == 0) return 0;

        int min = Integer.MAX_VALUE;
        if (dp[index] != -1) return dp[index];
        for (int i = 1; i <= k; i++) {
            if (index - i >= 0) {

                int jump = frogJumps_Recursive(index - i, heights, k) + Math.abs(heights[index] - heights[index - i]);
                min = Math.min(min, jump);
            }
        }

        return dp[index] = min;
    }

    public static int frogJumps_Tabular(int index, int heights[], int dp[], int k) {
//        if (index == 0) return 0;
        int n = heights.length;
        dp[0] = 0;

       for(int i = 1;i<n;i++){
            int min = Integer.MAX_VALUE;

           for (int j = 1; j <=k ; j++) {
               if (i - j>= 0){
                   int jump = dp[i-j] + Math.abs(heights[index] - heights[i-j]);
                   min = Math.min(min,jump);
               }
           }
           dp[i] = min;
       }

        return dp[n-1];
    }
}

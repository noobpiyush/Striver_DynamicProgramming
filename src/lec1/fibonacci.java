package lec1;

import java.util.Arrays;

public class fibonacci {

    public static void main(String[] args) {
        int dp[] = new int [5];
        Arrays.fill(dp,-1);
        System.out.println(fibonacci_Memoization(4,dp));
        System.out.println(fibonacci_tabular(4,dp));
        System.out.println(fibonacci_Recursive(4));
        System.out.println(fibonacci_optimised(4,dp));
    }

    public static int fibonacci_Recursive(int n){

        if (n <= 1){
            return n;
        }

        return fibonacci_Recursive(n-1) + fibonacci_Recursive(n-2);

    }

    public static int fibonacci_Memoization(int n, int dp[]){

        if (n <= 1){
            return n;
        }

        if (dp[n] != -1) return dp[n];

        return dp[n] = fibonacci_Recursive(n-1) + fibonacci_Recursive(n-2);

    }

    public static int fibonacci_tabular(int n, int dp[]){

        dp[0] = 0;

        dp[1] = 1;

        for (int i = 2 ; i <=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public static int fibonacci_optimised(int n, int dp[]){

        int prev2 = 0;

        int prev = 1;

        for (int i = 2 ; i <=n; i++) {
            int cur = prev + prev2;
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }

}

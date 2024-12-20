package minCostClimbStairs;

import java.util.ArrayList;
import java.util.Arrays;

public class minCostToClimb {

    public static void main(String[] args) {
        int[] cost = {10,15,20};
        int n = cost.length;

        System.out.println( Math.min( minCost(n-1,cost), minCost(n-2, cost) ) );

        System.out.println(minCostMemo(cost));

        System.out.println(minCostTab(cost));
    }

    public static int minCost(int index, int arr[]){

        if (index < 0) return 0;

        if (index == 1 || index == 0) return arr[index];

        return arr[index] + Math.min( minCost(index -1,arr), minCost(index -2,arr) );
    }

    public static int minCostMemo( int arr[]){
        int n = arr.length;

        int dp[] = new int[n];

        Arrays.fill(dp,-1);

        return Math.min( minCostMemoUtil(n-1, arr, dp), minCostMemoUtil(n-2, arr, dp) );
    }
    public static int minCostMemoUtil(int index, int arr[],int dp[]){

        if (index < 0) return 0;

        if (dp[index] != -1) return dp[index];

        if (index == 1 || index == 0) return arr[index];

        return dp[index] = arr[index] + Math.min( minCostMemoUtil(index -1,arr,dp), minCostMemoUtil(index -2,arr,dp) );
    }

    public static int minCostTab( int arr[]) {

        int n = arr.length;
        int dp[] = new int[n];

        dp[0] = arr[0];
        dp[1] = arr[1];

        for(int index = 2;index<n;index++){

            dp[index] = arr[index] + Math.min(dp[index - 1], dp[index - 2]);
        }

        return Math.min(dp[n-1], dp[n-2]);
    }

    public static int minCostOPTM(int arr[]) {

        int n = arr.length;


        int first = arr[0];
        int second = arr[1];

        for (int index = 2; index < n; index++) {

            int cur = arr[index] + Math.min(first, second);
            first = second;
            second = cur;
        }

        return Math.min(first,second);
    }

}

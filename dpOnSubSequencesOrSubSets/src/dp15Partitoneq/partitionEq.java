package dp15Partitoneq;

import java.util.Arrays;

public class partitionEq {

    public static void main(String[] args) {
        int arr[] = {2, 3, 3, 3, 4, 5};
        int n = arr.length;

        System.out.println(targetSumRec(arr, n));
        System.out.println(targetSumMemo(arr, n));

        System.out.println(targetSumTabulation(arr));

        System.out.println(targetSumSpaceOptimization(arr));
    }

    public static boolean targetSumRec(int arr[], int n) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if (sum % 2 == 1) {
            return false;
        } else {
            return targetSumRecUtil(n - 1, sum / 2, arr);
        }
    }

    public static boolean targetSumMemo(int arr[], int n) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        int k = sum/2;

        int dp[][] = new int[n][k+1];

        for (int rows[] : dp){
            Arrays.fill(rows,-1);
        }

        if (sum % 2 == 1) {
            return false;
        } else {
            return targetSumMemoUtil(n-1,sum/2,arr,dp);
        }
    }

    public static boolean targetSumRecUtil(int index, int sum, int arr[]) {

        //base case

        if (index == 0) {
            return arr[0] == sum;
        }

        if (sum == 0) {
            return true;
        }

        boolean notTake = targetSumRecUtil(index - 1, sum, arr);

        boolean take = false;

        if (arr[index] <= sum) {
            take = targetSumRecUtil(index - 1, sum - arr[index], arr);
        }

        return take || notTake;
    }

    public static boolean targetSumMemoUtil(int index, int sum, int arr[], int dp[][]) {

        //base case

        if (index == 0) {
            return arr[0] == sum;
        }

        if (sum == 0) {
            return true;
        }

        if (dp[index][sum] != -1) {
            return dp[index][sum] != 0;
        }

        boolean notTake = targetSumRecUtil(index - 1, sum, arr);

        boolean take = false;

        if (arr[index] <= sum) {
            take = targetSumRecUtil(index - 1, sum - arr[index], arr);
        }

        dp[index][sum] = take || notTake ? 1 : 0;

        return take || notTake;
    }

    public static boolean targetSumTabulation(int arr[]) {

        int tsum = 0;

        for (int i:arr){
            tsum += i;
        }

        if (tsum % 2 ==1){
            return false;
        }
        else {
            int k = tsum/2;
            boolean dp[][] = new boolean[arr.length][k+1];

            for (int i =0;i<arr.length;i++){
                dp[i][0] = true;
            }

            if (arr[0] <= k){
                dp[0][arr[0]] = true;
            }

            for (int i =1;i<arr.length;i++){
                for (int target = 0;target<=k;target++){
                    boolean notTake = dp[i-1][target];

                    boolean take = false;

                    if (arr[i] <= target){
                        take = dp[i-1][target - arr[i]];
                    }

                    dp[i][target] = take || notTake;
                }
            }
            return dp[arr.length-1][k];
        }
    }

    public static boolean targetSumSpaceOptimization(int arr[]) {

        int tsum = 0;

        for (int i : arr) {
            tsum += i;
        }

        if (tsum % 2 == 1) {
            return false;
        } else {
            int k = tsum / 2;
            boolean prev[] = new boolean[k+1];

             prev[0] = true;

            if (arr[0] <= k) {
                prev[arr[0]] = true;
            }

            for (int i = 1; i < arr.length; i++) {
                boolean cur[] = new boolean[k+1];
                cur[0] = true;
                for (int target = 1; target <= k; target++) {
                    boolean notTake = prev[target];

                    boolean take = false;

                    if (arr[i] <= target) {
                        take = prev[target - arr[i]];
                    }

                    cur[target] = take || notTake;
                }

                prev = cur;
            }
            return prev[k];
        }
    }
}

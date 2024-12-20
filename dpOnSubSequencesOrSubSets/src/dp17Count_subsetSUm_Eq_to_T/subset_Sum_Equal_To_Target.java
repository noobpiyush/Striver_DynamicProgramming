package dp17Count_subsetSUm_Eq_to_T;

import java.util.Arrays;

public class subset_Sum_Equal_To_Target {

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 3};
        int k = 3;

        int n = arr.length;

        System.out.println(countSubsetsRec(n - 1, k, arr));
        System.out.println(countSubsetsMemo(n - 1, k, n, arr));
        System.out.println(countSubsetsTabulation(k,arr));
        System.out.println(countSubsetsSpaceOptimization(k,arr));
    }

    public static int countSubsetsRec(int index, int sum, int arr[]) {
        //base case
        if (index == 0) {
            if (arr[index] == sum) {
                return 1;
            }
            return 0;
        }

        if (sum == 0) return 1;

        int notTake = countSubsetsRec(index - 1, sum, arr);

        int take = 0;

        if (arr[index] <= sum) {
            take = countSubsetsRec(index - 1, sum - arr[index], arr);
        }

        return take + notTake;
    }

    public static int countSubsetsMemo(int index, int sum, int n, int arr[]) {
        int dp[][] = new int[arr.length][sum + 1];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return countSubsetsMemoHelper(arr.length - 1, sum, arr, dp);
    }

    public static int countSubsetsMemoHelper(int index, int sum, int arr[], int dp[][]) {

        //base case

        if (index == 0) {
            if (sum == 0 && arr[0] == 0) return 2;
            if (sum == 0 || sum == arr[0]) {
                return 1;
            }
            return 0;
        }

        if (dp[index][sum] != -1) return dp[index][sum];

        int notTake = countSubsetsMemoHelper(index - 1, sum, arr,dp);

        int take = 0;

        if (arr[index] <= sum) {
            take = countSubsetsMemoHelper(index - 1, sum - arr[index], arr,dp);
        }

        return dp[index][sum] = take + notTake;
    }

    public static int countSubsetsTabulation(int sum, int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][sum + 1];
        //base case

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        if (arr[0] <= sum) {
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                int notTake = dp[i - 1][j];

                int take = 0;

                if (arr[i] <= j) {
                    take = dp[i - 1][j - arr[i]];
                }

                dp[i][j] = take + notTake;
            }
        }

        return dp[n - 1][sum];
    }

    public static int countSubsetsSpaceOptimization(int sum, int arr[]) {
        int n = arr.length;
        int prev[] = new int[sum + 1];
        //base case

        prev[0] = 1;

        if (arr[0] <= sum) {
            prev[arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            int cur[] = new int[sum + 1];

            cur[0] = 1;
            for (int j = 1; j <= sum; j++) {
                int notTake = prev[j];

                int take = 0;

                if (arr[i] <= j) {
                    take = prev[j - arr[i]];
                }

                cur[j] = take + notTake;
            }

            prev = cur;
        }

        return prev[sum];
    }
}
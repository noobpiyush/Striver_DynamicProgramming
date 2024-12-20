package Dp_18_SubsetDiff;

import java.util.Arrays;

public class subsetSum2 {
    public static void main(String[] args) {

        int arr[] = {5,2,6,4};
        int d=3;

        int n = arr.length;

        System.out.println(countSubSetsRec(d,n,arr));
        System.out.println(countSubSetsMemo(d,n,arr));
    }

    public static int countSubSetsRecUtil(int index,int target,int arr[]){

        if (index == 0){
            if (target == 0 && arr[0] == 0 ){
                return 2;
            }

            if (target == 0 || target == arr[0]){
                return 1;
            }

            return 0;
        }

        int notTake = countSubSetsRecUtil(index-1,target,arr);

        int take = 0;

        if (arr[index] <= target ){
            take = countSubSetsRecUtil(index - 1,target - arr[index],arr);
        }

        return take + notTake;
    }

    public static int countSubSetsMemoUtil(int index, int target, int arr[],int dp[][]) {

        if (index == 0) {
            if (target == 0 && arr[0] == 0) {
                return 2;
            }

            if (target == 0 || target == arr[0]) {
                return 1;
            }

            return 0;
        }

        if (dp[index][target] != -1) return dp[index][target];

        int notTake = countSubSetsMemoUtil(index - 1, target, arr,dp);

        int take = 0;

        if (arr[index] <= target) {
            take = countSubSetsMemoUtil(index - 1, target - arr[index], arr,dp);
        }

        return dp[index][target] = take + notTake;
    }

    public static int countSubSetsTabulationUtil(int index, int k, int arr[], int dp[][]) {

        if (arr[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;

        if (arr[0] != 0 && arr[0] <= k)dp[0][arr[0]] = 1;

        for (int ind = 1;ind < arr.length;ind++){

            for (int target =0;target<=k;target++){
                int notTake = dp[ind - 1][target];
                int take = 0;

                if (arr[index] <= target) {
                    take = dp[ind - 1][target - arr[ind]];
                }

                dp[ind][target] = take + notTake;
            }
        }

        return dp[arr.length -1][k];
    }

    public static int countSubSetsRec(int target,int n,int arr[]){

        int totalSum = 0;

        for (int i =0;i<n;i++){
            totalSum+=arr[i];
        }

        if (totalSum - target < 0) return 0;

        if ((totalSum - target) % 2 == 1) return 0;

        int newTarget = (totalSum - target )/2;


        return countSubSetsRecUtil(n-1,newTarget,arr);
    }

    public static int countSubSetsMemo(int target, int n, int arr[]) {

        int totalSum = 0;

        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }

        if (totalSum - target < 0) return 0;

        if ((totalSum - target) % 2 == 1) return 0;

        int newTarget = (totalSum - target) / 2;

        int dp[][] = new int[n][newTarget + 1];

        for (int rows[]: dp){
            Arrays.fill(rows,-1);
        }


        return countSubSetsMemoUtil(n - 1, newTarget, arr,dp);
    }

}

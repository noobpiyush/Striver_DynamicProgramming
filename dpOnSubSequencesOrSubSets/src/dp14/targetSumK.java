package dp14;

import java.util.Arrays;

public class targetSumK {

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4 };
        int k = 4;
        int n = arr.length;

        System.out.println(targetSumRecursive(n-1,k,arr));

        System.out.println(targetSumMemo(n,k,arr));

        System.out.println(targetSumTabulation(n,k,arr));

        System.out.println(targetSumOptimised(n,k,arr));
    }

    public static boolean targetSumRecursive(int index,int target,int[] arr){
        //base case

        if (target == 0){
            return true;
        }

        if (index == 0){
            return arr[0] == target;
        }

        boolean notTake =  targetSumRecursive(index-1,target,arr);

        boolean take = false;

        if (arr[index] >= target){
            take = targetSumRecursive(index-1,target - arr[index],arr);
        }

        return take || notTake;
    }

    public static boolean targetSumMemoHelper(int index,int target,int[] arr,int dp[][]){

        if (target == 0){
            return true;
        }

        if (index == 0){
            return arr[0] == target;
        }

        if (dp[index][target] != -1) return dp[index][target] != 0;

        boolean notTake =  targetSumRecursive(index-1,target,arr);

        boolean take = false;

        if (arr[index] >= target){
            take = targetSumRecursive(index-1,target - arr[index],arr);
        }

        dp[index][target] =  notTake || take ? 1 : 0;

        return take || notTake;
    }

    public static boolean targetSumTabulation(int n,int target,int[] arr){

        boolean dp[][] = new boolean[n][target+1];

        for (int i =0;i<n;i++){
            dp[i][0] = true;
        }

        if (arr[0] <= target){
            dp[0][arr[0]] = true;
        }

        for (int i = 1;i<n;i++){

            for (int k =1;k<=target;k++){
                boolean notTake;

                notTake = dp[i-1][k];

                boolean take  = false;

                if (arr[i] <= k ){
                    take = dp[i-1][k - arr[i]];
                }

                dp[i][k] = take || notTake;
            }

        }

        return dp[n-1][target];

    }
    public static boolean targetSumOptimised(int n, int k, int[] arr) {

       boolean prev[] = new boolean[k+1];

       prev[0] = true;


        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {

           boolean cur[] = new boolean[k+1];

           cur[0] = true;

           for (int target = 1;target<=k;target++){
                boolean not = prev[target];

                boolean take = false;
                if (arr[i] <= target){
                    take = prev[target - arr[i]];
                }

                cur[target] = take || not;
           }
            prev = cur;
        }

        return prev[k];

    }

    public static boolean targetSumMemo(int n,int k,int arr[]){
        int dp[][] = new int[n][k+1];

        for (int rows[] : dp){
            Arrays.fill(rows,-1);
        }

        return targetSumMemoHelper(n-1,k,arr,dp);
    }
}

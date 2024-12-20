package dp_22_coinChange2;

import java.util.Arrays;

public class minCoins2 {

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3 };
        int target = 4;
        int n = arr.length;

        System.out.println(allWaysRec(arr,n-1,target));

        System.out.println(allWaysMemo(arr,n-1,n,target));

        System.out.println(allWaysTabulation(arr,target));
    }

    public static int allWaysRec(int coins[],int index,int target){

        if (index == 0){
            if (target % coins[0] == 0){
                return 1;
            }
            else
                return 0;
        }

        int notTake = allWaysRec(coins,index-1,target);
        int take = 0;
        if (target >= coins[index]){
            take = allWaysRec(coins,index,target - coins[index]);
        }

        return notTake + take;
    }

    public static int allWaysMemo(int coins[], int index,int n ,int target){

        int dp[][] = new int[n][target + 1];

        for (int row[] : dp){
            Arrays.fill(row,-1);
        }

        return  allWaysMemoUtil(coins,n-1,target,dp);
    }

    public static int allWaysMemoUtil(int coins[], int index, int target,int dp[][]) {

        if (index == 0) {
            if (target % coins[0] == 0) {
                return 1;
            } else
                return 0;
        }

        if (dp[index][target] != -1) return dp[index][target];

        int notTake = allWaysMemoUtil(coins, index - 1, target,dp);
        int take = 0;
        if (target >= coins[index]) {
            take = allWaysMemoUtil(coins, index, target - coins[index],dp);
        }

        return dp[index][target] = notTake + take;
    }

    public static int allWaysTabulation(int coins[],int target) {
        int n = coins.length;
        int dp[][] = new int[n][target + 1];
        for (int i = 0;i<=target;i++){
            if (i % coins[0] == 0) {
                dp[0][i] = 1;
            }
        }

        for(int i = 1;i<n;i++){

            for (int t = 0;t<=target;t++){

                int notTake = dp[i-1][t];
                int take = 0;
                if (t >= coins[i]) {
                    take = dp[i][t - coins[i]];
                }

                dp[i][t] = notTake + take;
            }
        }

        return dp[n-1][target];
    }

    public static int allWaysOp(int coins[], int target) {
        int n = coins.length;
        int prev[] = new int[target + 1];
        int cur[] = new int[target + 1];
        for (int i = 0; i <= target; i++) {
            if (i % coins[0] == 0) {
                prev[i] = 1;
            }
        }

        for (int i = 1; i < n; i++) {

            for (int t = 0; t <= target; t++) {

                int notTake = prev[t];
                int take = 0;
                if (t >= coins[i]) {
                    take = cur[t - coins[i]];
                }

                cur[t] = notTake + take;
            }
            prev = cur;
        }

        return prev[target];
    }
}

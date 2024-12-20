package dp5;

import java.util.Arrays;

public class houseRobber1 {

    public static void main(String[] args) {
        int arr[] = {2, 1, 4, 9};

        int n = arr.length;

        System.out.println(houseRobber2_Recursive(arr,n,n-1));

        int dp[] = new int[n];

        Arrays.fill(dp,-1);

        System.out.println(houseRobber2_Memoization(arr,n,n-1,dp));

        System.out.println(houseRobber2_Tabular(arr,n,n-1,dp));
        System.out.println(houseRobber2_SpaceOptimization(arr, n));
    }

    public static int houseRobber2_Recursive(int houses[],int n,int index){
        if(index == 0) return houses[index];

        if (index < 0) return 0;

        int pick = houses[index] + houseRobber2_Recursive(houses,n,index-2);

        int notPick =  houseRobber2_Recursive(houses,n,index-1);

        return Math.max(pick,notPick);
    }

    public static int houseRobber2_Memoization(int houses[], int n, int index,int dp[]) {

       if (index == 0) return houses[index];
       if (index < 0) return 0;

       if (dp[index] != -1) return dp[index];

       int pick = houses[index] + houseRobber2_Memoization(houses,n,index-2,dp);

       int notPick = houseRobber2_Memoization(houses,n,index-1,dp);

       return dp[index] = Math.max(pick,notPick);
    }

    public static int houseRobber2_Tabular(int houses[], int n, int index, int dp[]) {

        dp[0] = houses[0];

        for (int i = 1; i < n; i++) {
            int pick = houses[i];

            if (i >1){
                pick += dp[i-2];
            }
            int nonPick = dp[i-1];

            dp[i] = Math.max(pick,nonPick);

        }

        return dp[n-1];
    }

    public static int houseRobber2_SpaceOptimization(int houses[], int n) {

        int prev2 = 0;
        int prev = houses[0];


        for (int i = 1; i < n; i++) {
            int pick = houses[i];

            if (i > 1) {
                pick += prev2;
            }
            int nonPick = prev;

            int max = Math.max(pick, nonPick);

            prev2 = prev;

            prev = max;

        }

        return prev;
    }
}

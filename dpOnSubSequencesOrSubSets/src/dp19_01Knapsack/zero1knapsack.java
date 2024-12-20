package dp19_01Knapsack;

import java.util.Arrays;

public class zero1knapsack {

    public static void main(String[] args) {
        int wt[] = {1, 2, 4, 5};
        int val[] = {5, 4, 8, 6};
        int W = 5;
        int n = wt.length;

        System.out.println(zeroOneRec(n-1,W,val,wt));
        System.out.println(zeroOneMemo(n,W,val,wt));

        System.out.println(zeroOneTabulation(W,val,wt));

        System.out.println(zeroOneSpace1(W,val,wt));
    }

    public static int zeroOneRec(int index,int W,int val[],int wt[]){
        if (index == 0){
            if (W >= wt[0]){
                return val[0];
            }
            return 0;
        }

        int notTake = zeroOneRec(index - 1, W, val, wt);

        int take = Integer.MIN_VALUE;

        if (W > wt[index]){
            take = val[index] + zeroOneRec(index - 1, W - wt[index],val,wt);
        }

        return Math.max(take,notTake);
    }

    public static int zeroOneMemo(int n,int W ,int val[], int wt[]){
        int dp[][] = new int[n][W+1];

        for (int rows[]:dp){
            Arrays.fill(rows,-1);
        }

        return zeroOneMemoUtil(n-1,W,val,wt,dp);
    }

    public static int zeroOneMemoUtil(int index, int W, int val[], int wt[],int dp[][]) {
        if (index == 0) {
            if (W >= wt[0]) {
                return val[0];
            }
            return 0;
        }

        if (dp[index][W] != -1) return dp[index][W];

        int notTake = zeroOneMemoUtil(index - 1, W, val, wt,dp);

        int take = Integer.MIN_VALUE;

        if (W > wt[index]) {
            take = val[index] + zeroOneMemoUtil(index - 1, W - wt[index], val, wt,dp);
        }

        return dp[index][W] =  Math.max(take, notTake);
    }

    public static int zeroOneTabulation(int W, int val[], int wt[]) {

        int n = val.length;

        int dp[][] = new int[n][W+1];

        for (int i = wt[0];i<=W;i++){
            dp[0][i] = val[0];
        }

        for (int ind = 1;ind<n;ind++){

            for (int weight = 0;weight<=W;weight++){

                int notTake = dp[ind -1][weight];

                int take = Integer.MIN_VALUE;

                if (weight > wt[ind]) {
                    take = val[ind] + dp[ind -1][weight - wt[ind]];
                }

                dp[ind][weight] = Math.max(take, notTake);
            }

        }

        return dp[n-1][W];
    }

    public static int zeroOneSpace1(int W, int val[], int wt[]) {

        int n = val.length;

        int prev[] = new int[W + 1];

        for (int i = wt[0]; i <= W; i++) {
            prev[i] = val[0];
        }

        for (int ind = 1; ind < n; ind++) {
                int cur[] = new int[W+1];
            for (int weight = 0; weight <= W; weight++) {

                int notTake = prev[weight];

                int take = Integer.MIN_VALUE;

                if (weight >= wt[ind]) {
                    take = val[ind] + prev[weight - wt[ind]];
                }

                cur[weight] = Math.max(take, notTake);
            }
            prev = cur;
        }

        return prev[W];
    }

}

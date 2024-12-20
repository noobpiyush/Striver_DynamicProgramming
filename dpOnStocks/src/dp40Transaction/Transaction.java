package dp40Transaction;

import java.util.Arrays;

public class Transaction {

    public static void main(String[] args) {
        int prices[] = {1, 3, 2, 8, 4, 9};
        int n = prices.length;
        int fee = 2;
        System.out.println(stockTransactionRec(0, 0, n, prices, fee));
        System.out.println(stockTransactionMemo(n,prices,fee));
        System.out.println(stockTransactionTab(prices,fee));
    }

    public static int stockTransactionRec(int ind, int buy, int n, int[] prices, int fee) {
        //base case

        if (ind >= n) return 0;

        long profit = 0;

        if (buy == 0) {
            profit = Math.max(stockTransactionRec(ind + 1, 0, n, prices, fee), -prices[ind] + stockTransactionRec(ind + 1, 1, n, prices, fee));
        }
        if (buy == 1) {
            //we can sell
            profit = Math.max(stockTransactionRec(ind + 1, 1, n, prices, fee), prices[ind] - fee + stockTransactionRec(ind + 1, 0, n, prices, fee));
        }

        return (int) profit;
    }

    public static int stockTransactionMemo(int n, int[] prices, int fee) {

        int dp[][] = new int[n + 1][2];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return stockTransactionMemoUtil(0, 0, n, prices, fee, dp);
    }

    public static int stockTransactionMemoUtil(int ind, int buy, int n, int[] prices, int fee, int[][] dp) {
        //base case

        if (ind >= n) return 0;

        if (dp[ind][buy] != -1) return dp[ind][buy];

        long profit = 0;

        if (buy == 0) {
            profit = Math.max(
                    stockTransactionMemoUtil(ind + 1, 0, n, prices, fee, dp),
                    -prices[ind] + stockTransactionMemoUtil(ind + 1, 1, n, prices, fee, dp)
            );
        }
        if (buy == 1) {
            //we can sell
            profit = Math.max(stockTransactionMemoUtil(ind + 1, 1, n, prices, fee,dp),
                    prices[ind] - fee + stockTransactionMemoUtil(ind + 1, 0, n, prices, fee,dp)
            );
        }

        return dp[ind][buy] = (int) profit;
    }

    public static int stockTransactionTab(int[] prices, int fee) {
        //base case
        int n = prices.length;

        int dp[][] = new int[n+1][2];

        long profit = 0;

        for (int  ind = n-1; ind >= 0; ind-- ){

            for (int buy = 0; buy <= 1;buy++){

                if (buy == 0) {
                    profit = Math.max(
                             dp[ind+1][0] ,
                            -prices[ind] + dp[ind+1][1]
                    );
                }
                if (buy == 1) {
                    //we can sell
                    profit = Math.max(dp[ind+1][1],
                            prices[ind] - fee + dp[ind+1][0]
                    );
                }

                 dp[ind][buy] = (int) profit;

            }
        }
        return dp[0][0];
    }


}

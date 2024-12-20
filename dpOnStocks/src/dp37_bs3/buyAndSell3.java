package dp37_bs3;

import java.util.Arrays;

public class buyAndSell3 {

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;

        System.out.println(buyNSell3Rec(0, 0, 2, n, prices));
        System.out.println(
                buyNSell3Memo(n, prices)
        );

        System.out.println(buyNSell3Tab(n,prices));
    }

    public static int buyNSell3Rec(int index, int buy, int cap, int n, int prices[]) {
        if (index == n || cap == 0) {
            return 0;
        }

        long profit = 0;

        if (buy == 0) {
            profit = Math.max(-prices[index] + buyNSell3Rec(index + 1, 1, cap, n, prices), buyNSell3Rec(index + 1, 0, cap, n, prices));
        }
        if (buy == 1) {
            //we can sell
            profit = Math.max(prices[index] + buyNSell3Rec(index + 1, 0, cap - 1, n, prices), buyNSell3Rec(index + 1, 1, cap, n, prices));
        }
        return (int) profit;
    }

    public static int buyNSell3Memo(int n, int prices[]) {

        int dp[][][] = new int[n][2][3];

        for (int rows[][] : dp) {
            for (int row[] : rows) {
                Arrays.fill(row, -1);
            }
        }
        return buyNSell3MemoUtil(0, 0, 2, n, prices, dp);
    }

    public static int buyNSell3MemoUtil(int index, int buy, int cap, int n, int prices[], int dp[][][]) {
        if (index == n || cap == 0) {
            return 0;
        }

        if (dp[index][buy][cap] != -1) return dp[index][buy][cap];

        long profit = 0;

        if (buy == 0) {
            profit = Math.max(-prices[index] + buyNSell3MemoUtil(index + 1, 1, cap, n, prices, dp), buyNSell3MemoUtil(index + 1, 0, cap, n, prices, dp));
        }
        if (buy == 1) {
            //we can sell
            profit = Math.max(prices[index] + buyNSell3MemoUtil(index + 1, 0, cap - 1, n, prices, dp), buyNSell3MemoUtil(index + 1, 1, cap, n, prices, dp));
        }
        return dp[index][buy][cap] = (int) profit;
    }

    public static int buyNSell3Tab(int n, int prices[]) {

        int dp[][][] = new int[n + 1][2][3];

        for (int ind = n - 1; ind >= 0; ind--) {

            for (int buy = 0; buy <= 1; buy++) {

                for (int cap = 1; cap <= 2; cap++) {

                    if (buy == 0) {
                        dp[ind][buy][cap] = Math.max( dp[ind + 1][0][cap],
                                -prices[ind] + dp[ind + 1][1][cap]);
                    }
                    if (buy == 1) {
                        //we can sell
                        dp[ind][buy][cap] = Math.max(prices[ind] + dp[ind+1][0][cap-1], dp[ind+1][1][cap]);
                    }

                }
            }
        }

        return dp[0][0][2];
    }


}

package dp36_buyNSell2;

import java.util.Arrays;

public class buyAndSell2 {

    public static void main(String[] args) {

        int prices[] = {7, 1, 5, 3, 6, 4};

        int n = prices.length;

        System.out.println(bsRec(0, n, 0, prices));

        System.out.println(bsMemo(prices, n));

        System.out.println(bsTab(n,prices));
    }

    public static int bsRec(int ind, int n, int buy, int arr[]) {
        if (ind == n) {
            return 0;
        }

        long profit = 0;
        if (buy == 0) {
            //we can buy the stock

            profit = Math.max(bsRec(ind + 1, n, 0, arr),
                    -arr[ind] + bsRec(ind + 1, n, 1, arr)
            );
        }
        if (buy == 1) {
            //we can sell the stock
            profit = Math.max(bsRec(ind + 1, n, 1, arr),
                    arr[ind] + bsRec(ind + 1, n, 0, arr)
            );
        }

        return (int) profit;
    }

    public static int bsMemo(int arr[], int n) {

        int dp[][] = new int[n][2];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return bsMemoUtil(0, n, 0, arr, dp);

    }

    private static int bsMemoUtil(int ind, int n, int buy, int arr[], int[][] dp) {

        if (ind == n) {
            return 0;
        }

        if (dp[ind][buy] != -1) return dp[ind][buy];

        long profit = 0;
        if (buy == 0) {
            //we can buy the stock

            profit = Math.max(bsMemoUtil(ind + 1, n, 0, arr, dp),
                    -arr[ind] + bsMemoUtil(ind + 1, n, 1, arr, dp)
            );
        }
        if (buy == 1) {
            //we can sell the stock
            profit = Math.max(bsMemoUtil(ind + 1, n, 1, arr, dp),
                    arr[ind] + bsMemoUtil(ind + 1, n, 0, arr, dp)
            );
        }

        return dp[ind][buy] = (int) profit;
    }

    public static int bsTab(int n, int arr[]) {
        int[][] dp = new int[n + 1][2];

        dp[n][0] = dp[n][1] = 0;

        long profit = 0;

        for (int ind = n - 1; ind >= 0; ind--) {

            for (int buy = 0; buy <= 1; buy++) {

                if (buy == 0) {
                    //we can buy the stock

                    profit = Math.max(dp[ind + 1][0],
                            -arr[ind] + dp[ind + 1][1]
                    );
                }
                if (buy == 1) {
                    //we can sell the stock
                    profit = Math.max(dp[ind + 1][1],
                            arr[ind] + dp[ind + 1][0]
                    );
                }
                dp[ind][buy] = (int) profit;
            }
        }

        return dp[0][0];
    }

}

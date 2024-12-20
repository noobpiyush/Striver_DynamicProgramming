package dp39;
import java.util.Arrays;
public class coolDown {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};

        int n = prices.length;

        System.out.println(stockCoolDownRec(0, 0, n, prices));

        System.out.println(stockCoolDownMemo(n, prices));

        System.out.println(stockCoolDownTab(n,prices));
    }

    public static int stockCoolDownRec(int ind, int buy, int n, int[] prices) {
        //base case

        if (ind >= n) return 0;

        long profit = 0;

        if (buy == 0) {
            profit = Math.max(stockCoolDownRec(ind + 1, 0, n, prices), -prices[ind] + stockCoolDownRec(ind + 1, 1, n, prices));
        }
        if (buy == 1) {
            //we can sell
            profit = Math.max(stockCoolDownRec(ind + 1, 1, n, prices), prices[ind] + stockCoolDownRec(ind + 2, 0, n, prices));
        }

        return (int) profit;
    }

    public static int stockCoolDownMemo(int n, int prices[]) {

        int dp[][] = new int[n + 1][2];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return stockCoolDownMemoUtil(0, 0, n, prices, dp);

    }

    public static int stockCoolDownMemoUtil(int ind, int buy, int n, int[] prices, int dp[][]) {
        //base case

        if (ind >= n) return 0;

        if (dp[ind][buy] != -1) return dp[ind][buy];

        long profit = 0;

        if (buy == 0) {
            profit = Math.max(stockCoolDownMemoUtil(ind + 1, 0, n, prices, dp), -prices[ind] + stockCoolDownMemoUtil(ind + 1, 1, n, prices, dp));
        }
        if (buy == 1) {
            //we can sell
            profit = Math.max(stockCoolDownMemoUtil(ind + 1, 1, n, prices, dp), prices[ind] + stockCoolDownMemoUtil(ind + 2, 0, n, prices, dp));
        }

        return dp[ind][buy] = (int) profit;
    }

    public static int stockCoolDownTab(int n, int[] prices) {
        //base case

        int dp[][] = new int[n + 2][2];

        long profit = 0;

        for (int ind = n - 1; ind >= 0; ind--) {

            for (int buy = 0; buy <= 1; buy++) {

                if (buy == 0) {
                    profit = Math.max(dp[ind + 1][0], -prices[ind] + dp[ind + 1][1]);
                }
                if (buy == 1) {
                    //we can sell
                    profit = Math.max(dp[ind + 1][1], prices[ind] + dp[ind + 2][0]);
                }

                dp[ind][buy] = (int) profit;

            }
        }
        return dp[0][0];
    }
}

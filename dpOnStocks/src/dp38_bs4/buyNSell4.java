package dp38_bs4;

public class buyNSell4 {

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        int k = 2;

        System.out.println(buyNSell3Rec(0,0,k,n,prices));
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
}

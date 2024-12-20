package dp35BSS1;

public class buyAndSell1 {

    public static void main(String[] args) {
        int arr[] = {7,1,5,3,6,4};
        System.out.println(buyAndSellStocks(arr));
    }

    public static int buyAndSellStocks(int arr[]){
        int maxProfit = 0;
        int min = arr[0];

        for(int i =1;i<arr.length;i++){
           int profit = arr[i] - min;
            maxProfit = Math.max(profit,maxProfit);
            min  = Math.min(min,arr[i]);
        }
        return maxProfit;
    }
}

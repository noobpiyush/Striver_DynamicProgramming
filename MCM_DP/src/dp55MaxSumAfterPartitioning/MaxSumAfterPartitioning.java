package dp55MaxSumAfterPartitioning;

import java.util.Arrays;

public class MaxSumAfterPartitioning {

    public static void main(String[] args) {

        int[] num = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        int n = num.length;

        System.out.println(maxSumRec(0,k,n,num));

        System.out.println(maxSumMemo(num,k,n));
    }

    public static int maxSumRec(int i,int k, int n, int arr[]){

        if (i == n) return 0;

        int max = Integer.MIN_VALUE;

        int maxAns = Integer.MIN_VALUE;

        int len = 0;

        for (int j = i;j<Math.min(i + k,n); j++){

            len++;
            max = Math.max(max,arr[j]);
            int sum = len* max + maxSumRec(j +1,k,n,arr);
            maxAns = Math.max(sum,maxAns);
        }
        return maxAns;
    }

    public static int maxSumMemo(int arr[], int k, int n){

        int dp[] = new int[n + 1];

        Arrays.fill(dp,-1);

        return maxSumMeoUtil(0,k,n,arr,dp);

     }

    public static int maxSumMeoUtil(int i, int k, int n, int arr[],int dp[]) {

        if (i == n) return 0;

        if (dp[i] != -1) return dp[i];

        int max = Integer.MIN_VALUE;

        int maxAns = Integer.MIN_VALUE;

        int len = 0;

        for (int j = i; j < Math.min(i + k, n); j++) {

            len++;
            max = Math.max(max, arr[j]);
            int sum = len * max + maxSumMeoUtil(j + 1, k, n, arr,dp);
            maxAns = Math.max(sum, maxAns);
        }

        return dp[i] = maxAns;
    }

    public static int maxSumTab(int k, int n, int arr[]) {

        int dp[] = new int[n + 1];

        for (int i = n-1;i>=0;i--){

            int max = Integer.MIN_VALUE;

            int maxAns = Integer.MIN_VALUE;

            int len = 0;

            for (int j = i; j < Math.min(i + k, n); j++) {

                len++;
                max = Math.max(max, arr[j]);
                int sum = len * max +  dp[j+1];
                maxAns = Math.max(sum, maxAns);
            }

            dp[i] = maxAns;
        }

        return dp[0] ;
    }
}

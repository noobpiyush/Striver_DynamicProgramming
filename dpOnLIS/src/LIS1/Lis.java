package LIS1;

import java.util.Arrays;

public class Lis {

    public static void main(String[] args) {
        int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = arr.length;

        System.out.println(LIS_Rec(0, -1, n, arr));

        System.out.println(LIS_Memo(n,arr));
    }

    public static int LIS_Rec(int index, int prev, int n, int arr[]) {

        if (index == n) return 0;

        int notTake = LIS_Rec(index + 1, prev, n, arr);

        int take = 0;

        if (prev == -1 || arr[index] > arr[prev]) {

            take = 1 + LIS_Rec(index + 1, index, n, arr);
        }

        return Math.max(take, notTake);
    }

    public static int LIS_Memo(int n, int arr[]) {

        int dp[][] = new int[n][n + 1];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return LIS_MemoUtil(0, -1, n, arr, dp);
    }

    public static int LIS_MemoUtil(int index, int prev, int n, int arr[], int dp[][]) {

        if (index == n) return 0;

        if (dp[index][prev + 1] != -1) return dp[index][prev + 1];

        int notTake = LIS_MemoUtil(index + 1, prev, n, arr, dp);

        int take = 0;

        if (prev == -1 || arr[index] > arr[prev]) {

            take = 1 + LIS_MemoUtil(index + 1, index, n, arr, dp);
        }

        return dp[index][prev + 1] = Math.max(take, notTake);

    }

    public static int LIS_Tabulation(int n, int arr[]) {

        int dp[][] = new int[n+1][n+1];


        for (int index = n -1;index >= 0; index--){

            for (int prev = index-1;prev>=-1;prev--){

                int notTake = dp[index+1][prev+1];

                int take = 0;

                if (prev == -1 || arr[index] > arr[prev]) {

                    take = 1 + dp[index+1][index + 1];
                }

                dp[index][prev + 1] = Math.max(take, notTake);
            }

        }

        return dp[0][0];
    }
}

package dp48_49_MCM;

import java.util.Arrays;

public class MCM {

    public static void main(String[] args) {
        int arr[] = {10, 20, 30, 40, 50};

        int n = arr.length;

        int staart = 1, end = n - 1;

        System.out.println(MCM_Rec(staart, end, n, arr));

        System.out.println(MCM_Memo(n, arr));

        System.out.println(MCM_Tab(n,arr));
    }

    public static int MCM_Rec(int start, int end, int n, int arr[]) {

        if (start == end) return 0;

        int mini = Integer.MAX_VALUE;

        for (int k = start; k <= end - 1; k++) {

            int ans = MCM_Rec(start, k, n, arr) + MCM_Rec(k + 1, end, n, arr)
                    + arr[start - 1] * arr[k] * arr[end];

            mini = Math.min(mini, ans);
        }

        return mini;
    }

    public static int MCM_Memo(int n, int arr[]) {

        int dp[][] = new int[n][n];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return MCM_MemoUtil(1, n - 1, n, arr, dp);
    }

    public static int MCM_MemoUtil(int start, int end, int n, int arr[], int dp[][]) {

        if (start == end) return 0;

        if (dp[start][end] != -1) return dp[start][end];

        int mini = Integer.MAX_VALUE;

        for (int k = start; k <= end - 1; k++) {

            int ans = MCM_MemoUtil(start, k, n, arr, dp) + MCM_MemoUtil(k + 1, end, n, arr, dp)
                    + arr[start - 1] * arr[k] * arr[end];

            mini = Math.min(mini, ans);
        }

        return dp[start][end] = mini;
    }

    public static int MCM_Tab(int n, int arr[]) {

        int dp[][] = new int[n][n];

        for (int i = n - 1; i >= 1; i--) {

            for (int j = i + 1; j < n; j++) {

                int mini = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {

                    int ans = dp[i][k] + dp[k + 1][j]
                            + arr[i - 1] * arr[k] * arr[j];

                    mini = Math.min(mini, ans);
                }

                dp[i][j] = mini;
            }

        }

        return dp[1][n-1];
    }

}

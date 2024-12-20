package dp51_burstBallons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class burstCoins {


    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoinsRec(nums));
        System.out.println(maxCoinsMemo(nums));
        System.out.println(maxCoinsTab(nums));
    }

    public static int maxCoinsRec(int arr[]) {

        int n = arr.length;

        List<Integer> coins = new ArrayList<>();

        for (int ele : arr) {
            coins.add(ele);
        }

        coins.add(1);
        coins.add(0, 1);

        return maxCoinsRecUtil(1, n, coins);

    }

    public static int maxCoinsRecUtil(int i, int j, List<Integer> arr) {

        if (i > j) return 0;

        int maxi = Integer.MIN_VALUE;

        for (int ind = i; ind <= j; ind++) {

            int ans = arr.get(ind) * arr.get(i - 1) * arr.get(j + 1) + maxCoinsRecUtil(i, ind - 1, arr) + maxCoinsRecUtil(ind + 1, j, arr);

            maxi = Math.max(maxi, ans);

        }

        return maxi;
    }

    public static int maxCoinsMemo(int arr[]) {

        int n = arr.length;

        List<Integer> coins = new ArrayList<>();

        for (int ele : arr) {
            coins.add(ele);
        }

        coins.add(1);
        coins.add(0, 1);

        int dp[][] = new int[n + 2][n + 2];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return maxCoinsMemoUtil(1, n, coins, dp);

    }

    public static int maxCoinsMemoUtil(int i, int j, List<Integer> arr, int dp[][]) {

        if (i > j) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int maxi = Integer.MIN_VALUE;

        for (int ind = i; ind <= j; ind++) {

            int ans = arr.get(ind) * arr.get(i - 1) * arr.get(j + 1) + maxCoinsMemoUtil(i, ind - 1, arr, dp) + maxCoinsMemoUtil(ind + 1, j, arr, dp);

            maxi = Math.max(maxi, ans);

        }

        return dp[i][j] = maxi;
    }

    public static int maxCoinsTab(int[] nums) {

        int n = nums.length;

        List<Integer> arr = new ArrayList<>();

        for (int ele : nums) {
            arr.add(ele);
        }

        arr.add(1);
        arr.add(0, 1);

        int dp[][] = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {

            for (int j = 1; j <= n; j++) {

                int maxi = Integer.MIN_VALUE;

                if (i > j) continue;

                for (int ind = i; ind <= j; ind++) {

                    int ans = arr.get(ind) * arr.get(i - 1) * arr.get(j + 1)
                            + dp[i][ind - 1] + dp[ind + 1][j];

                    maxi = Math.max(maxi, ans);

                }
                dp[i][j] = maxi;
            }
        }

        return dp[1][n];
    }


}

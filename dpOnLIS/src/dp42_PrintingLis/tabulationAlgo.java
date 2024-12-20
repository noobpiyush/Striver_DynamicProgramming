package dp42_PrintingLis;

import java.util.ArrayList;
import java.util.Arrays;

public class tabulationAlgo {

    public static void main(String[] args) {
        int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is " + LisTabulationAlgo(n, arr));

        System.out.println(PrintLisTabulationAlgo(n, arr));

    }

    public static int LisTabulationAlgo(int n, int arr[]) {

        int dp[] = new int[n];

        Arrays.fill(dp, 1);

        for (int ind = 0; ind < n; ind++) {

            for (int prev = 0; prev <= ind - 1; prev++) {

                if (arr[ind] > arr[prev]) {
                    dp[ind] = Math.max(dp[ind], 1 + dp[prev]);
                }
            }
        }

        int ans = -1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int PrintLisTabulationAlgo(int n, int arr[]) {

        int dp[] = new int[n];
        int hash[] = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(hash, 1);


        for (int ind = 0; ind < n; ind++) {
            hash[ind] = ind;
            for (int prev = 0; prev <= ind - 1; prev++) {

                if (arr[ind] > arr[prev] && 1 + dp[prev] > dp[ind]) {
                    dp[ind] = 1 + dp[prev];
                    hash[ind] = prev;
                }
            }
        }

        int ans = -1;
        int lastIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > ans) {
                ans = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(arr[lastIndex]);

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            temp.add(arr[lastIndex]);
        }

        int j = 0;
        for (int i = temp.size() - 1; i >= 0; i--) {
            temp.set(j, temp.get(i));
        }

        return ans;
    }
}

package dp53PallindromePartitioning2;

import java.util.Arrays;

public class pallindromePartitioning {

    public static void main(String[] args) {

        String str = "BABABCBADCEDE";

        int n = str.length();

        int ans = minWaysRec(0, n, str);

        System.out.println(ans - 1);

        System.out.println(minWaysMemo(str));

        System.out.println(minWaysTab(str));
    }

    private static boolean isPallindrome(int i, int j, String str) {

        while (i < j) {

            if (str.charAt(i) != str.charAt(j)) {

                return false;
            }

            i++;

            j--;
        }

        return true;
    }

    public static int minWaysRec(int i, int n, String str) {

        if (i == n) return 0;

        int minCost = Integer.MAX_VALUE;

        for (int j = i; j < n; j++) {

            if (isPallindrome(i, j, str)) {

                int cost = 1 + minWaysRec(j + 1, n, str);

                minCost = Math.min(minCost, cost);
            }
        }
        return minCost;
    }

    public static int minWaysMemo(String str) {

        int n = str.length();

        int dp[] = new int[n + 1];

        Arrays.fill(dp, -1);

        return minWaysMemoUtil(0, n, str, dp) - 1;
    }

    public static int minWaysMemoUtil(int i, int n, String str, int dp[]) {

        if (i == n) return 0;

        if (dp[i] != -1) return dp[i];

        int minCost = Integer.MAX_VALUE;

        for (int j = i; j < n; j++) {

            if (isPallindrome(i, j, str)) {

                int cost = 1 + minWaysMemoUtil(j + 1, n, str, dp);

                minCost = Math.min(minCost, cost);
            }
        }
        return dp[i] = minCost;
    }

    public static int minWaysTab(String str) {

        int n = str.length();

        int dp[] = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {

            int minCost = Integer.MAX_VALUE;

            for (int j = i; j < n; j++) {

                if (isPallindrome(i, j, str)) {

                    int cost = 1 + dp[j +1];

                    minCost = Math.min(minCost, cost);
                }
            }

            dp[i] = minCost;
        }

        return dp[0] - 1;
    }

}

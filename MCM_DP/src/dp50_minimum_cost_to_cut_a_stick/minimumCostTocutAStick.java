package dp50_minimum_cost_to_cut_a_stick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class minimumCostTocutAStick {

    public static void main(String[] args) {
        int[] cuts = {1, 3, 4, 5};

        int n = 7;

        int c = cuts.length;

        System.out.println(minCostRec(n, cuts));

        System.out.println(minCostMemo(n,cuts));

        System.out.println(minCostTab(c,n, cuts));
    }

    public static int minCostRec(int n, int cuts[]) {

        List<Integer> cutsList = new ArrayList<>();

        for (int ele : cuts) {
            cutsList.add(ele);
        }


        int c = cuts.length;

        cutsList.add(n);
        cutsList.add(0);

        Collections.sort(cutsList);

        return minCostRecUtil(1, c, n, cutsList);

    }

    public static int minCostRecUtil(int i, int j, int n, List<Integer> cutsList) {

        if (i > j) return 0;

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {

            int ans = cutsList.get(j + 1) - cutsList.get(i - 1) + minCostRecUtil(i, ind - 1, n, cutsList)
                    + minCostRecUtil(ind + 1, j, n, cutsList);

            mini = Math.min(ans, mini);

        }
        return mini;
    }

    public static int minCostMemo(int n, int arr[]) {
        List<Integer> cutsList = new ArrayList<>();

        for (int ele : arr) {
            cutsList.add(ele);
        }

        int c = arr.length;

        cutsList.add(n);
        cutsList.add(0);
        Collections.sort(cutsList);

        int dp[][] = new int[c + 1][c + 1];

        for (int rows[] : dp) Arrays.fill(rows, -1);

        return minCostMemoUtil(1, c, cutsList, dp);
    }

    private static int minCostMemoUtil(int i, int j, List<Integer> cutsList, int dp[][]) {

        if (i > j) return 0;

        int mini = Integer.MAX_VALUE;

        if (dp[i][j] != -1) return dp[i][j];

        for (int ind = i; ind <= j; ind++) {

            int ans = cutsList.get(j + 1) - cutsList.get(i - 1) + minCostMemoUtil(i, ind - 1, cutsList, dp)
                    + minCostMemoUtil(ind + 1, j, cutsList, dp);

            mini = Math.min(ans, mini);

        }
        return dp[i][j] = mini;

    }

    public static int minCostTab(int c, int n, int arr[]){

        List<Integer> cutsList = new ArrayList<>();

        for (int ele : arr) {
            cutsList.add(ele);
        }

        cutsList.add(n);
        cutsList.add(0);
        Collections.sort(cutsList);

        int dp[][] = new int[c + 2][c + 2];

        for (int i = c; i>= 1; i--){

            for (int j = 1; j<= c;j++){

                int mini = Integer.MAX_VALUE;

                if (i> j) continue;

                for (int ind = i; ind <= j; ind++) {

                    int ans =  cutsList.get(j + 1) - cutsList.get(i - 1) +  dp[i][ind-1] + dp[ind + 1][j];

                    mini = Math.min(ans, mini);

                }

                dp[i][j] = mini;
            }
        }
        return dp[1][c];
    }

}

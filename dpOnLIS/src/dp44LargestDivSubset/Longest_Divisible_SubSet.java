package dp44LargestDivSubset;

import java.util.*;

public class Longest_Divisible_SubSet {

    public static void main(String[] args) {

    }

    public static List<Integer> LongestDivisibleSubSet(int arr[]) {

        int n = arr.length;

        // Sort the array
        Arrays.sort(arr);

        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            hash[i] = i; // Initializing with current index
        }

        for (int i = 1; i < n; i++) {
            for (int prev_index = 0; prev_index < i; prev_index++) {
                if (arr[i] % arr[prev_index] == 0 && 1 + dp[prev_index] > dp[i]) {
                    dp[i] = 1 + dp[prev_index];
                    hash[i] = prev_index;
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

        List<Integer> temp = new ArrayList<>();
        temp.add(arr[lastIndex]);

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            temp.add(arr[lastIndex]);
        }

        // Reverse the list to get the correct order
        Collections.reverse(temp);

        return temp;
    }
}

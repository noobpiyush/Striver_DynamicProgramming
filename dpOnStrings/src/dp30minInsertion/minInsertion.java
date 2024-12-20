package dp30minInsertion;

import java.util.Arrays;

public class minInsertion {
    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "anc";

        System.out.println(minInsertions(str1,str2));
    }
    public static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create a 2D array to store the LCS lengths
        int dp[][] = new int[n + 1][m + 1];



        // Fill the dp array using a bottom-up approach
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                else
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
            }
        }

        return dp[n][m];

    }

    public static int minInsertions(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int common = lcs(s1,s2);

        return  (n - common) + (m - common);
    }

}


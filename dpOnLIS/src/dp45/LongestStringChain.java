package dp45;

import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain {
    static Comparator<String> comp = (s1,s2) -> s1.length() - s2.length();
    public static void main(String[] args) {
        String [] words = {"a","b","ba","bca","bda","bdca"};
        int n = words.length;

        System.out.println(longestStringChain(words));
    }

    private static boolean isTrue(String s1, String s2){

        int n = s1.length();
        int m = s2.length();

        if (n != m+1) return false;

        int first =0;
        int second = 0;

        while (first < n ){

            if (second < m && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;
            }
            else{
                first++;
            }
        }
        return first == n && second == m;
    }

    public static int longestStringChain( String  arr[]){
        int n = arr.length;

        Arrays.sort(arr,comp);

        int dp[] = new int[n];

        Arrays.fill(dp,1);

        int maxi = 1;

        for (int i =0;i<n;i++){

            for (int prev =0; prev < i; prev++){

                if (isTrue(arr[i], arr[prev]) && 1 + dp[prev] > dp[i] ){

                    dp[i] = 1 + dp[prev];

                }

            }

           maxi = Math.max(maxi,dp[i]);
        }

        return maxi;
    }
}

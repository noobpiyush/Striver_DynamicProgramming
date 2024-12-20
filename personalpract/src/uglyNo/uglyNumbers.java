package uglyNo;

import java.util.Arrays;

public class uglyNumbers {

    public static void main(String[] args) {
//        System.out.println(findUglyRecursive(7));
        System.out.println(findUglyMemoization(7));
    }

    public static boolean isUglyRecursiveHelper(int n){
        if (n == 1) return true;
        if (n <=0 ) return false;

        if (n % 2 == 0) return isUglyRecursiveHelper(n/2);
        if (n % 3 == 0) return isUglyRecursiveHelper(n/3);
        if (n % 5 == 0) return isUglyRecursiveHelper(n/5);

        return false;
    }

    public static boolean isUglyMemoHelper(int n, boolean dp[]) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (dp[n]) return true;

        if (n % 2 == 0) dp[n] = isUglyMemoHelper(n / 2, dp);
        else if (n % 3 == 0) dp[n] = isUglyMemoHelper(n / 3, dp);
        else if (n % 5 == 0) dp[n] = isUglyMemoHelper(n / 5, dp);
        else dp[n] = false;

        return dp[n];
    }

    public static boolean isUglyTabular(int n, boolean dp[]) {
       dp[1] = true;

        for (int i = 2 ; i <=n ; i++) {
            if (i % 2 == 0 && dp[i/2]){
                dp[i] = true;
            }
        }
        return false;
    }

    public  static int findUglyRecursive(int  n){
        int current_number = 0;
        int count  = 0;

        while (count < n){
            current_number++;
            if (isUglyRecursiveHelper(n)){
                count++;
            }

        }
        return current_number;
    }

    public static int findUglyMemoization(int n) {
        boolean dp[] = new boolean[1690];  // Use a sufficiently large array for memoization
        Arrays.fill(dp, false);

        int current_number = 0;
        int count = 0;

        while (count < n) {
            current_number++;
            if (isUglyMemoHelper(current_number, dp)) {
                count++;
            }
        }
        return current_number;
    }

}

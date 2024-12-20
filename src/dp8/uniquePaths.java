package dp8;

import java.util.Arrays;

public class uniquePaths {

    public static void main(String[] args) {
        System.out.println(countWaysRHelper(2,1));
//        System.out.println(countWaysMemo(3,2));
        System.out.println(countWaysTabular(3,2));
    }

    static  int countWaysRHelper(int i,int j){
        if (i == 0 && j == 0) return 1;

        if ( i< 0 || j < 0) return 0;

        int up = countWaysRHelper(i -1,j);

        int left = countWaysRHelper(i,j-1);

        return up + left;
    }

    static int countWaysMemoH(int i, int j,int dp[][]) {


        if (i == 0 && j == 0) return dp[i][j] =  1;

        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int up = countWaysRHelper(i - 1, j);

        int left = countWaysRHelper(i, j - 1);

        return dp[i][j] =  up + left;
    }

    static int countWaysTabular(int m, int n){
        int dp[][] = new int[m][n];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return countWaysTabularHelper(m,n,dp);
    }
    static int countWaysTabularHelper(int m, int n,int dp[][]){

        for (int i = 0; i< m;i++){

            for(int j =0; j< n; j++){
                if (i ==0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }

                int up = 0, left = 0;

                if (i > 0){
                    up = dp[i - 1][j];
                }
                if (j > 0){
                    left =dp[i][j-1];
                }

                dp[i][j] = up + left;
            }
        }

        return dp[m-1][n-1];
    }

    static int countWaysMemo(int m, int n) {
        int dp[][] = new int[m][n];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return countWaysMemoH(m , n , dp);
    }

    static int countWaysSpaceOpt(int m, int n) {
        int prev[] = new int[m];
        for (int i = 0; i < m; i++) {
            int temp[] = new int[n];

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0){
                    temp[j] = 1;
                    continue;
                }
                int up =0,left = 0;

                if (i > 0){
                    up = prev[j];
                }
                if (j > 0){
                    left = temp[j-1];
                }

                temp[j] = up + left;
            }
            prev = temp;
        }
        return prev[n-1];
    }


}

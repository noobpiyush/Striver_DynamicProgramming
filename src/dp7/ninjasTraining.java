package dp7;

import java.util.Arrays;

public class ninjasTraining {

    public static void main(String[] args) {
        int[][] points = {{10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}};

        int n = points.length; // Get the number of days

        System.out.println(ninjaT_Recursive(points,n));

       int dp[][] = new int[n][4];

       for(int row[]:dp){
           Arrays.fill(row,-1);
       }

        System.out.println(ninjaT_Memoization(points,dp,n-1,3));
    }

    public  static int ninjaT_Recursive(int point[][],int n){
        int day = n -1;
        int last = 3;

        return ninjaT_RecursiveHelper(point,day,last);
    }

    public static int ninjaT_RecursiveHelper(int point[][], int day, int last) {
        if (day == 0){
            int maxi = 0;

            for (int i = 0; i <=2; i++) {
                if (i != last){
                    maxi = Math.max(point[0][i],maxi);
                }
            }

            return maxi;
        }
        int maxi = Integer.MIN_VALUE;

        for (int lastindex = 0; lastindex <=2; lastindex++) {
            if (lastindex != last){
                int points = point[day][lastindex] + ninjaT_RecursiveHelper(point,day-1,lastindex);
                maxi = Math.max(points,maxi);
            }
        }
        return maxi;
    }

    public static int ninjaT_Memoization(int point[][],int dp[][] ,int day, int last) {
        if(dp[day][last] != -1) return dp[day][last];
        if (day == 0) {
            int maxi = 0;

            for (int i = 0; i <= 2; i++) {
                if (i != last) {
                    maxi = Math.max(point[0][i], maxi);
                }
            }

            return dp[day][last] = maxi;
        }
        int maxi = Integer.MIN_VALUE;

        for (int lastindex = 0; lastindex <= 2; lastindex++) {
            if (lastindex != last) {
                int points = point[day][lastindex] + ninjaT_RecursiveHelper(point, day - 1, lastindex);
                maxi = Math.max(points, maxi);
            }
        }
        return dp[day][last] = maxi;
    }

    public static int ninjaT_Tab(int points[][]) {
        int n = points.length;
        int dp[][] = new int[n][4];

        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        int maxi = Integer.MIN_VALUE;

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0; // Initialize the maximum points for the current day and last activity
                // Consider each possible task for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) { // Ensure that the current task is different from the last
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        int activity = points[day][task] + dp[day - 1][task];
                        // Update the maximum points for the current day and last activity
                        dp[day][last] = Math.max(dp[day][last], activity);
                    }
                }
            }
        }
        return dp[n - 1][3];
    }
}

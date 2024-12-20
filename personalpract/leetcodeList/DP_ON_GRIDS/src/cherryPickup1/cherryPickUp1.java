package cherryPickup1;

import java.util.Arrays;

public class cherryPickUp1 {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        };

        int n = grid.length;

        System.out.println(maxCherriesRec( n-1, n-1, n-1, n-1, grid ));

        System.out.println(maxCherriesMemo(grid));
    }

    public static int maxCherriesRec(int r1, int c1, int r2, int c2, int grid[][]){

        if (r1 < 0 || c1 < 0 || r2 < 0 || c2  <  0 || grid[r1][c1] == -1 || grid[r2][c2] == -1 ){
            return Integer.MIN_VALUE;
        }

        if (r1 == 0 && c1 == 0) return grid[r1][c1];

        if (r2 == 0 && c2 == 0) return grid[r2][c2];

        int cherries;

        if (r1 == r2 && c1 == c2){
            cherries = grid[r1][c1];
        }else{
            cherries = grid[r1][c1] + grid[r2][c2];
        }

        return cherries += Math.max(
                Math.max(
                        maxCherriesRec(r1 - 1, c1, r2 - 1, c2, grid), // Both paths move up
                        maxCherriesRec(r1 - 1, c1, r2, c2 - 1, grid) // First path up, second path left
                ),
                Math.max(
                        maxCherriesRec(r1, c1 - 1, r2 - 1, c2, grid), // First path left, second path up
                        maxCherriesRec(r1, c1 - 1, r2, c2 - 1, grid) // Both paths move left
                )
        );

    }

    public static int maxCherriesMemo(int grid[][]){
        int n = grid.length;

//        int dp [][][][] = new int [n][n][n][n];
//
//        for(int rows[][][] : dp){
//
//            for(int row[][] : rows){
//
//                for(int ros[] : row){
//
//                    Arrays.fill(ros,-1);
//                }
//            }
//        }

        Integer[][][][] dp = new Integer[n][n][n][n];

        return Math.max(0, maxCherriesMemoUtil(n-1,n-1,n-1,n-1,grid,dp));
    }

    public static int maxCherriesMemoUtil(int r1, int c1, int r2, int c2, int grid[][], Integer[][][][] dp){

        if (r1 < 0 || c1 < 0 || r2 < 0 || c2  <  0 || grid[r1][c1] == -1 || grid[r2][c2] == -1 ){
            return Integer.MIN_VALUE;
        }

        if (r1 == 0 && c1 == 0) return grid[r1][c1];

        if (r2 == 0 && c2 == 0) return grid[r2][c2];

        if (dp[r1][c1][r2][c2] != null) return dp[r1][c1][r2][c2];

        int cherries;

        if (r1 == r2 && c1 == c2){
            cherries = grid[r1][c1];
        }else{
            cherries = grid[r1][c1] + grid[r2][c2];
        }

        return dp[r1][c1][r2][c2] = cherries += Math.max(
                Math.max(
                        maxCherriesMemoUtil(r1 - 1, c1, r2 - 1, c2, grid,dp), // Both paths move up
                        maxCherriesMemoUtil(r1 - 1, c1, r2, c2 - 1, grid,dp) // First path up, second path left
                ),
                Math.max(
                        maxCherriesMemoUtil(r1, c1 - 1, r2 - 1, c2, grid,dp), // First path left, second path up
                        maxCherriesMemoUtil(r1, c1 - 1, r2, c2 - 1, grid,dp) // Both paths move left
                )
        );

    }
}

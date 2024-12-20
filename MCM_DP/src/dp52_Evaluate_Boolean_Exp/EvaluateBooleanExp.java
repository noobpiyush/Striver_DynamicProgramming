package dp52_Evaluate_Boolean_Exp;

import java.util.Arrays;

public class EvaluateBooleanExp {
    static int MOD = 1003;

    public static void main(String[] args) {
        String exp = "T^F|F";
        int n = exp.length();
        int ways = minWaysRec(exp, 0, n - 1, 1);
        System.out.println("The total number of ways (recursive): " + ways);
        System.out.println("The total number of ways (memoization): " + minWaysMemo(exp));
        System.out.println(minWaysTab(exp));
    }

    public static int minWaysRec(String exp, int i, int j, int isTrue) {
        if (i > j) return 0;

        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        long ways = 0;

        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = minWaysRec(exp, i, ind - 1, 1);
            long lF = minWaysRec(exp, i, ind - 1, 0);
            long rT = minWaysRec(exp, ind + 1, j, 1);
            long rF = minWaysRec(exp, ind + 1, j, 0);

            char operand = exp.charAt(ind);

            if (operand == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operand == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else { // operand == '^'
                if (isTrue == 1) {
                    ways = (ways + (lT * rF) % MOD + (lF * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lT * rT) % MOD + (lF * rF) % MOD) % MOD;
                }
            }
        }
        return (int) ways;
    }

    public static int minWaysMemo(String exp) {
        int n = exp.length();
        int[][][] dp = new int[n][n][2];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }
        return minWaysMemoUtil(exp, 0, n - 1, 1, dp);
    }

    private static int minWaysMemoUtil(String exp, int i, int j, int isTrue, int[][][] dp) {
        if (i > j) return 0;

        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        if (dp[i][j][isTrue] != -1) return dp[i][j][isTrue];

        long ways = 0;

        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = minWaysMemoUtil(exp, i, ind - 1, 1, dp);
            long lF = minWaysMemoUtil(exp, i, ind - 1, 0, dp);
            long rT = minWaysMemoUtil(exp, ind + 1, j, 1, dp);
            long rF = minWaysMemoUtil(exp, ind + 1, j, 0, dp);

            char operand = exp.charAt(ind);

            if (operand == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operand == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else { // operand == '^'
                if (isTrue == 1) {
                    ways = (ways + (lT * rF) % MOD + (lF * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lT * rT) % MOD + (lF * rF) % MOD) % MOD;
                }
            }
        }
        return dp[i][j][isTrue] = (int) ways;
    }

    private static int minWaysTab(String exp) {
        int n = exp.length();

        int[][][] dp = new int[n][n][2];

        for(int i = n-1;i>=0;i--){

            for (int j = 0; j<= n-1;j++){

                if (i > j) continue;

                for (int isTrue =  0;isTrue<=1;isTrue++){

                    if (i == j){
                        if (isTrue == 1) {
                            dp[i][j][isTrue] = exp.charAt(i) == 'T' ? 1 : 0;
                        } else {
                            dp[i][j][isTrue] =  exp.charAt(i) == 'F' ? 1 : 0;
                        }
                        continue;
                    }

                    long ways = 0;

                    for (int ind = i + 1; ind <= j - 1; ind += 2) {
                        long lT = dp[i][ind -1][1];
                        long lF = dp[i][ind -1][0];
                        long rT = dp[ind + 1][j][1];
                        long rF = dp[ind + 1][j][0];

                        char operand = exp.charAt(ind);

                        if (operand == '&') {
                            if (isTrue == 1) {
                                ways = (ways + (lT * rT) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                            }
                        } else if (operand == '|') {
                            if (isTrue == 1) {
                                ways = (ways + (lT * rT) % MOD + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rF) % MOD) % MOD;
                            }
                        } else { // operand == '^'
                            if (isTrue == 1) {
                                ways = (ways + (lT * rF) % MOD + (lF * rT) % MOD) % MOD;
                            } else {
                                ways = (ways + (lT * rT) % MOD + (lF * rF) % MOD) % MOD;
                            }
                        }
                    }
                    dp[i][j][isTrue] = (int) ways;
                }
            }
        }
        return dp[0][n-1][1];
    }
}

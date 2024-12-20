package greatestSumDivby3;

import java.util.Arrays;

public class greatestSumDivisiblebyThree {

    public static void main(String[] args) {
        int nums[] = {3, 6, 5, 1, 8};
        int n = nums.length;
        System.out.println(subsetSumDivBy3Rec(nums, n - 1, 0));
        System.out.println(subsetSumDivBy3Memo(nums));
        System.out.println(subsetSumDivBy3Tab(nums));
    }


    public static int subsetSumDivBy3Rec(int nums[], int index, int sum) {

        if (index < 0) {
            if (sum % 3 != 0){
                return Integer.MIN_VALUE;
            }
            return 0;
        }

        return Math.max(nums[index] +
                        subsetSumDivBy3Rec(nums, index - 1, (sum + nums[index]))
                , subsetSumDivBy3Rec(nums, index - 1, sum)

        );
    }

    public static int subsetSumDivBy3Memo(int nums[]) {
        int n = nums.length;
        int dp[][] = new int[n][3];

        for (int rows[] : dp) {
            Arrays.fill(rows, -1);
        }

        return subsetSumDivBy3MemoUtil(nums, n - 1, 0, dp);
    }

    public static int subsetSumDivBy3MemoUtil(int nums[], int index, int reminder, int dp[][]) {

        if (index < 0) return reminder == 0 ? 0 : Integer.MIN_VALUE;

        if (dp[index][reminder] != -1) return dp[index][reminder];

        return dp[index][reminder] = Math.max(nums[index] +
                        subsetSumDivBy3MemoUtil(nums, index - 1, (reminder + nums[index]) % 3,dp)
                , subsetSumDivBy3MemoUtil(nums, index - 1, reminder,dp)

        );
    }

    public static int subsetSumDivBy3Tab(int nums[] ) {

        int n = nums.length;
        int dp[][] = new int[n + 1][3];

        for(int i =0;i<3;i++){
            dp[0][i] = Integer.MIN_VALUE;
        }



        for (int index =  1;index<=n;index++){

            for(int reminder= 0; reminder<=2;reminder++ ){

                dp[index][reminder] = Math.max(nums[index] +
                                dp[index-1][(reminder + nums[index]) % 3]
                        , dp[index-1][reminder]
                );
            }
        }


        return dp[n-1][2];
    }

}

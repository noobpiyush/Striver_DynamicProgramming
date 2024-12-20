package Dp46LongestBitonic;

import java.util.Arrays;

public class LongestBitonic {

    public static void main(String[] args) {

        int arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
        int n = arr.length;

        System.out.println(longestBitonicSet(n,arr));
    }

    public static int longestBitonicSet(int n, int nums[]){

        int dp1[] = new int[n];

        int dp2[] = new int[n];

        Arrays.fill(dp2,1);

        Arrays.fill(dp1,1);


        for (int ind = 0;ind <n;ind++){

            for (int prevInd = 0; prevInd < ind; prevInd++){

                if (nums[ind] > nums[prevInd] && 1 + dp1[prevInd] > dp1[ind]){

                    dp1[ind] = 1 + dp1[prevInd];

                }

            }

        }

        int maxi = 0;
        for (int ind = n-1; ind >= 0; ind--) {

            for (int prevInd = n-1; prevInd > ind; prevInd--) {

                if (nums[ind] > nums[prevInd] && 1 + dp2[prevInd] > dp2[ind]) {

                    dp2[ind] = 1 + dp2[prevInd];

                }
            }

            maxi = Math.max(maxi,dp1[ind] + dp2[ind] - 1);

        }

        return maxi;

    }
}

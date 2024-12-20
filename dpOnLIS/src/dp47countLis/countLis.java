package dp47countLis;

import java.util.Arrays;

public class countLis {


    public static void main(String[] args) {

        int[] arr = {1,5,4,3,2,6,7,2};

        System.out.println(countLIS(arr));
    }

    public static int countLIS(int nums[]){

        int n = nums.length;

        int dp1[] = new int[n];

        int dp2[] = new int[n];

        Arrays.fill(dp2,1);

        Arrays.fill(dp1,1);

        int maxi = 0;

        for (int ind = 0;ind <n;ind++){

            for (int prevInd = 0; prevInd < ind; prevInd++){

                if (nums[ind] > nums[prevInd] && 1 + dp1[prevInd] > dp1[ind]){

                    dp1[ind] = 1 + dp1[prevInd];
                    dp2[ind] = dp2[prevInd];

                }
                else if (nums[ind] > nums[prevInd] && 1 + dp1[prevInd] == dp1[ind]){
                    dp2[ind] += dp2[prevInd];
                }

            }
            maxi = Math.max(maxi,dp1[ind]);
        }

        int nos =0;

        for(int i=0; i<=n-1; i++){
            if(dp1[i]==maxi) nos+=dp2[i];
        }

        return  nos;
    }
}

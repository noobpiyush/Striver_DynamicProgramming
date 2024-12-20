package dp6;

import java.util.ArrayList;
import java.util.List;

public class houseRobberTwo {
    public static void main(String[] args) {

    }
    public static int houseRobberTwo_SpaceOptimizationHelper(List<Integer> temp){
        int n = temp.size();
        int prev = temp.get(0);

        int prev2 = 0;


        for (int i = 1; i < n; i++) {
            int pick = temp.get(i);

            if (i >1){
                pick += prev2;
            }

            int nonPick = prev;

            int curi = Math.max(pick,nonPick);

            prev2 = prev;

            prev = curi;
        }

        return prev;
    }

    public static int houseRobberTwo_SpaceOptimization( int arr[], int n) {

        List<Integer> temp1 = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != 1) temp1.add(arr[i]);
            if (i != n-1) temp2.add(arr[i]);
        }

        int ans1 = houseRobberTwo_SpaceOptimizationHelper(temp1);
        int ans2 = houseRobberTwo_SpaceOptimizationHelper(temp2);

        return Math.max(ans1,ans2);
    }
}

package dp43;

import LIS1.Lis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BS_ON_LIS {

    public static void main(String[] args) {

        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = arr.length;

        System.out.println(Lis_Using_BS(arr,n));
    }

    public static int Lis_Using_BS(int arr[], int n) {

        List<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);
        int len = 1;
        for (int i = 1; i < n; i++) {

            if (arr[i] > temp.get(temp.size() - 1)) {
                temp.add(arr[i]);
                len++;
            }
            else {

                int ind = Collections.binarySearch(temp, arr[i]);

                if (ind < 0) {
                    ind = -ind - 1;
                }

                temp.set(ind, arr[i]);
            }
        }

        return len;
    }
}

package QNS;

//3a)
//You are given an even length array; divide it in half and return possible minimum product difference of any two subarrays.
//Note that the minimal product difference is the smallest absolute difference between any two arrays a and b, which is computed by calculating the difference after multiplying each element of the arrays a and b.
//Input: {5,2,4,11}
//Output: 2
//{5,4} {2,11} result into minimum product difference.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q3_a{
    public static int getMinProductDiff(int[] nums) {
        Arrays.sort(nums); // sort the input array
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        // calculate the difference between two sub arrays with the minimum product difference
        for (int i = 0; i < n / 2; i++) {
            int product1 = nums[i] * nums[n - i - 1];
            int product2 = nums[n / 2 + i] * nums[n - n / 2 - i - 1];
            int diff = Math.abs(product1 - product2);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 4, 11};
        System.out.println("The minimum product difference is: " + getMinProductDiff(nums)); // outputs 2
    }
}
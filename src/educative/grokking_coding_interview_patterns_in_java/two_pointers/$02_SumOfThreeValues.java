package educative.grokking_coding_interview_patterns_in_java.two_pointers;

import java.util.Arrays;

/**
 * Given an array of integers, nums, and an integer value, target, determine if
 * there are any three integers in nums whose sum equals the target.
 * Return TRUE if three such integers are found in the array. Otherwise, return FALSE.
 *
 * constraints:
 * - 3 <= nums.length <= 10000
 * - -1000 <= nums[i] <= 1000
 * - -1000 <= target <= 1000
 */
public class $02_SumOfThreeValues {
    public static void main(String[] args) {

    }

    public static boolean solution(int[] nums, int target) {
        // parameter validation
        if (nums.length < 3) {
            return false;
        }

        // sort nums in ascending order
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int currentNum = nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = currentNum + nums[left] + nums[right];
                if (sum == target) {
                    return true;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return false;
    }
}

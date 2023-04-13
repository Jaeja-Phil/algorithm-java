package educative.grokking_coding_interview_patterns_in_java.sliding_window;

/**
 * Given an array of positive integers nums and a positive integer target,
 * find the window size of the shortest contiguous subarray whose sum is
 * greater than or equal to the target value.
 * If no subarray is found, 0 is returned.
 *
 * constraints:
 * - 1 <= target <= 10^9
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^4
 *
 * ex)
 * target = 7
 * nums = [2, 3, 1, 2, 4, 3]
 * output = 2
 * explanation: the subarray [4, 3] has the minimal length under the problem constraint.
 */
public class $06_MinSizeSubarraySum {
    public static void main(String[] args) {
        System.out.println(solution(7, new int[] {2, 3, 1, 2, 4, 3})); // 2
    }

    public static int solution(int target, int[] nums) {
        // initialize left & right pointers
        int left = 0, right = 0;
        // initialize currentSum to 0 since no iteration has been done yet
        int currentSum = 0;
        // initialize minLen to the maximum value of integer
        // minLen will be updated when the currentSum is greater than or equal to the target
        int minLen = Integer.MAX_VALUE;

        // iterate through the nums array
        while (right < nums.length) {
            // get the value of the right window and slide the right window by one
            int rightNum = nums[right++];
            // add the value to the currentSum
            currentSum += rightNum;
            // if the currentSum is greater than or equal to the target...
            if (currentSum >= target) {
                // slide the left window until the currentSum is less than the target
                while (currentSum >= target) {
                    int leftNum = nums[left++];
                    currentSum -= leftNum;
                }
                // update the minLen
                minLen = Math.min(minLen, right - left + 1);
            }
        }

        // if minLen is still the maximum value of integer, return 0, else return minLen
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}

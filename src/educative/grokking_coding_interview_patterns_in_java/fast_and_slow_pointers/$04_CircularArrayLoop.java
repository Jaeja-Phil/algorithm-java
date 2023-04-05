package educative.grokking_coding_interview_patterns_in_java.fast_and_slow_pointers;

/**
 * An input array containing non-zero integers is given, where the value at each index
 * represents the number of places to skip forward (if the value is positive) or backward (if the value is negative).
 * When skipping forward or backward, wrap around if you reach either end of the array.
 * For this reason, we are calling it a circular array. Determine if this circular array has a cycle.
 * A cycle is a sequence of indices in the circular array characterized by the following:
 * - The same set of indices is repeated when the sequence is traversed in accordance with the aforementioned rules.
 * - The length of the sequence is at least two.
 * - The loop must be in a single direction, forward or backward.
 *
 * constraints:
 * - 1 <= arr.length <= 5000
 * - -1000 <= arr[i] <= 1000
 * - arr[i] != 0
 *
 * ex)
 * [1, 3, -2, -4, 1]
 * - step 1: 0 --> 1
 * - step 2: 1 --> 4
 * - step 3: 4 --> 0
 * output: true
 */
public class $04_CircularArrayLoop {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 3, -2, -4, 1})); // true
        System.out.println(solution(new int[] {2, 1, -1, -2})); // false
        System.out.println(solution(new int[] {1, -1, 5, 1, 4})); // false
    }

    public static boolean solution(int[] nums) {
        int slowIdx = 0, fastIdx = 0;
        do {
            slowIdx = nextIdx(nums, slowIdx);
            fastIdx = nextIdx(nums, nextIdx(nums, fastIdx));
        } while (slowIdx != fastIdx);

        // length of the cycle must be greater than 1
        if (slowIdx == nextIdx(nums, slowIdx)) {
            return false;
        }

        // check whether the cycle is in a single direction
        // record original direction
        boolean originalDirectionIsForward = nums[slowIdx] > 0;
        // set a pointer to the slow pointer
        int currIdx = slowIdx;
        while (true) {
            // move the pointer to the next index
            currIdx = nextIdx(nums, currIdx);
            // if the direction is different from the original direction, then the cycle is not in a single direction
            if (originalDirectionIsForward != nums[currIdx] > 0) {
                return false;
            }
            // if the pointer meets the slow pointer, then the cycle is in a single direction
            if (currIdx == slowIdx) {
                return true;
            }
        }
    }

    private static int nextIdx(int[] nums, int currIdx) {
        int numsLength = nums.length;
        int nextIdx = (currIdx + nums[currIdx]) % numsLength;
        return nextIdx < 0 ? nextIdx + numsLength : nextIdx;
    }
}

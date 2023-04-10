package educative.grokking_coding_interview_patterns_in_java.sliding_window;

import java.util.*;

/**
 * Given an integer list, nums, find the maximum values in all the contiguous subarrays (windows) of size w.
 *
 * constraints:
 * - 1 <= arr.length <= 1000
 * - -10000 <= arr[i] <= 10000
 * - 1 <= w
 *
 * ex)
 * nums = [-4, 5, 4, -4, 4, 6, 7, 20]
 * w = 2
 * output = [5, 5, 4, 4, 6, 7, 20]
 *
 * ex)
 * nums = [-4, 5, 4, -4, 4 , 6, 7]
 * w = 10
 * output = 7
 * explanation: the window size is greater than the length of the array, so return the maximum value in the array
 */
public class $02_FindMaximumInSlidingWindow {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[] {-4, 5, 4, -4, 4, 6, 7, 20}, 2))); // [5, 5, 4, 4, 6, 7, 20]
        System.out.println(Arrays.toString(solution(new int[] {-4, 5, 4, -4, 4, 6, 7}, 10))); // [7]
        // same using solution2
        System.out.println(Arrays.toString(solution2(new int[] {-4, 5, 4, -4, 4, 6, 7, 20}, 2))); // [5, 5, 4, 4, 6, 7, 20]
        System.out.println(Arrays.toString(solution2(new int[] {-4, 5, 4, -4, 4, 6, 7}, 10))); // [7]
    }

    public static int[] solution(int[] nums, int w) {
        // constraint: 1 <= arr.length <= 1000, so no need to check the length == 0 of nums

        // initialize the left & right pointers
        int left = 0, right = 0;

        // create maxHeap to store the maximum value in the sliding window
        // use node class with index to remove the value that is out of the sliding window
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> b.val - a.val);

        // create answer list
        List<Integer> answer = new ArrayList<>();

        // iterate through the nums array
        for (int i = 0; i < nums.length; i++) {
            // add the current value to the maxHeap
            maxHeap.offer(new Node(nums[i], i));

            // expand right side of the sliding window
            right++;
            // when the sliding window reaches the size of w...
            if (right - left == w) {
                // remove the value that is out of the sliding window
                while (maxHeap.peek().idx < left) {
                    maxHeap.poll();
                }
                // add the maximum value in the sliding window to the answer list
                answer.add(maxHeap.peek().val);
                // shrink left side of the sliding window
                left++;
            }
        }

        // answer of size 0 means that the window size is greater than the length of the array
        if (answer.size() == 0) {
            // add the maximum value in the array to the answer list
            answer.add(maxHeap.peek().val);
        }

        // return the answer list as int[]
        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * change the above solution's maxHeap to deque
     */
    public static int[] solution2(int[] nums, int w) {
        // constraint: 1 <= arr.length <= 1000, so no need to check the length == 0 of nums

        // initialize the left & right pointers
        int left = 0, right = 0;

        // create deque to store the maximum value in the sliding window
        // use node class with index to remove the value that is out of the sliding window
        Deque<Node> deque = new LinkedList<>();

        // create answer list
        List<Integer> answer = new ArrayList<>();

        // iterate through the nums array
        for (int i = 0; i < nums.length; i++) {
            // add the current value to the deque
            while (!deque.isEmpty() && deque.peekLast().val < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(new Node(nums[i], i));

            // expand right side of the sliding window
            right++;
            // when the sliding window reaches the size of w...
            if (right - left == w) {
                // remove the value that is out of the sliding window
                while (deque.peekFirst().idx < left) {
                    deque.pollFirst();
                }
                // add the maximum value in the sliding window to the answer list
                answer.add(deque.peekFirst().val);
                // shrink left side of the sliding window
                left++;
            }
        }

        // answer of size 0 means that the window size is greater than the length of the array
        if (answer.size() == 0) {
            // add the maximum value in the array to the answer list
            answer.add(deque.peekFirst().val);
        }

        // return the answer list as int[]
        return answer.stream().mapToInt(i -> i).toArray();
    }

    static class Node {
        int val;
        int idx;
        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}

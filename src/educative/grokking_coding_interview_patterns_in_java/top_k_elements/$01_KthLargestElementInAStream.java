package educative.grokking_coding_interview_patterns_in_java.top_k_elements;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an infinite stream of integers `nums`, design a class to find the kth
 * largest element in a stream
 *
 * class should have the following functions, inputs, and return values:
 * - init(): takes an array of integers and an integer k and initializes the class object
 * - add(value): takes an integer value from the stream and calls the kth largest element
 * - return kth largest(): returns an integer value that represents the kth largest element in the stream.
 *
 * constraints:
 * - 1 <= k <= 1000
 * - 0 <= nums.length <= 1000
 * - -1000 <= nums[i] <= 1000
 * - -1000 <= value <= 1000
 * - at most 1000 calls will be made to add
 * - guaranteed that there will be at least k elements in the array when you search for the kth element
 *
 * ex:
 * input:
 *   k = 6
 *   nums = [6, 8, 7, 5, 9, 4, 2, 3]
 * output:
 *   4
 * explanation:
 *   sorted array = [2, 3, "4", 5, 6, 7, 8, 9]
 */
public class $01_KthLargestElementInAStream {
    public static void main(String[] args) {
        int[] nums = {6, 8, 7, 5, 9, 4, 2, 3};
        int k = 6;
        KthLargest kthLargest = new KthLargest(k, nums);
        System.out.println(kthLargest.returnKthLargest()); // 4
    }

    public static class KthLargest {
        private PriorityQueue<Integer> minHeap;
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.minHeap = new PriorityQueue<>(Comparator.naturalOrder());

            for (int num : nums) {
                minHeap.offer(num);
            }

            while (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return returnKthLargest();
        }

        public int returnKthLargest() {
            return minHeap.peek();
        }
    }
}

package educative.grokking_coding_interview_patterns_in_java.k_way_merge;

import java.util.ArrayList;
import java.util.List;

/**
 * Given 2 sorted integer arrays, nums1 and nums2, and the number of data
 * elements in each array, m and n, implement a function that merges the second
 * array into the first one. You have to modify nums1 in place.
 *
 * constraints:
 * - nums1.length = m + n
 * - nums2.length = n
 * - 0 <= m, n <= 200
 * - 1 <= m + n <= 200
 * - -1000 <= nums1[i], nums2[i] <= 1000
 *
 * ex:
 * input:
 *   nums1 = [3, 4, 9, 0, 0, 0]
 *   nums2 = [1, 2, 7]
 *   m = 3
 *   n = 3
 * output:
 *   [1, 2, 3, 4, 7, 9]
 */
public class $01_MergeSortedArray {
    public static void main(String[] args) {
        List<Integer> nums1 = new ArrayList<>(List.of(3, 4, 9, 0, 0, 0));
        List<Integer> nums2 = new ArrayList<>(List.of(1, 2, 7));
        int m = 3;
        int n = 3;
        List<Integer> result = solution(nums1, nums2, m, n);
        System.out.println(result); // [1, 2, 3, 4, 7, 9]
    }

    public static List<Integer> solution(List<Integer> nums1, List<Integer> nums2, int m, int n) {
        // create index pointers for nums1(i) and nums2(j)
        int i = m - 1;
        int j = n - 1;
        // starting from the end of the array, insert the largest element at the end of nums1
        for (int p = m + n - 1; p >= 0; p--) {
            // if pointer to nums2 is less than 0, we're done
            if (j < 0) {
                break;
            }

            // if pointer for nums1 is greater than 0 and
            // nums1's current element is greater than nums2's current element
            // set nums1's current element at pointer p of nums1
            // and decrement pointer i
            if (i >= 0 && nums1.get(i) > nums2.get(j)) {
                nums1.set(p, nums1.get(i));
                i--;
            } else {
                // otherwise, set nums2's current element at pointer p of nums1
                // and decrement pointer j
                nums1.set(p, nums2.get(j));
                j--;
            }
        }

        return nums1;
    }
}

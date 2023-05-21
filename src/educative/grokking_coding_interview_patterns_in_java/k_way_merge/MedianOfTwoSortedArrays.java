package educative.grokking_coding_interview_patterns_in_java.k_way_merge;

import java.util.List;

/**
 * Given 2 sorted integer arrays, nums1 and nums2, of size m and n respectively, return the median of
 * the 2 sorted arrays.
 *
 * constraints:
 * - nums1.length == m, 0 <= m <= 1000
 * - nums2.length == n, 0 <= n <= 1000
 * - 1 <= m + n <= 2000
 * - -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * ex:
 * nums1: [1, 5, 7, 8]
 * nums2: [4, 7, 9, 11, 13, 15]
 * output: 7.5, (7 + 8) / 2
 */
public class MedianOfTwoSortedArrays {
    public static double solution(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length, nums2Length = nums2.length;
        int wholeLength = nums1Length + nums2Length;
        int[] newArr = new int[wholeLength];
        int i = 0, j = 0, k = 0;
        while (i <= nums1Length && j <= nums2Length) {
            if (i == nums1Length) {
                while (j < nums2Length) {
                    newArr[k++] = nums2[j++];
                }
                break;
            }
            if (j == nums2Length) {
                while (i < nums1Length) {
                    newArr[k++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                newArr[k++] = nums1[i++];
            } else {
                newArr[k++] = nums2[j++];
            }
        }

        if (wholeLength % 2 == 0) {
            return (newArr[wholeLength / 2] + newArr[wholeLength / 2 - 1]) / 2.0;
        } else {
            return newArr[wholeLength / 2];
        }
    }
}

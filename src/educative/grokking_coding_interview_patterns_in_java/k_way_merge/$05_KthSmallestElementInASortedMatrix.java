package educative.grokking_coding_interview_patterns_in_java.k_way_merge;

import java.util.List;
import java.util.PriorityQueue;

/**
 * find the kth smallest element in an n x n matrix, where each row and column is sorted in ascending order.
 *
 * constraints:
 * - there can be repeating values in the matrix and contributes to calculating the kth smallest element
 * - n == matrix.length
 * - n == matrix[i].length
 * - 1 <= n <= 300
 * - -10^9 <= matrix[i][j] <= 10^9
 * - 1 <= k <= n^2
 *
 * ex:
 * input =
 * matrix = [
 *   [2, 6, 8],
 *   [3, 6, 10],
 *   [5, 8, 11]
 * ]
 * k = 5
 * output = 6
 * explanation: [2, 3, 5, 6, "6", 8, 8, 10, 11]
 */
public class $05_KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {2, 6, 8},
                {3, 6, 10},
                {5, 8, 11}
        };
        int k = 5;
        int result = solution(matrix, k);
        System.out.println(result); // 6
    }

    public static int solution(int[][] matrix, int k) {
        int rowCount = matrix.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < rowCount; i++) {
            // push first element of each row in the minHeap
            // 0th index: value
            // 1st index: row
            // 2nd index: col
            minHeap.offer(new int[] {matrix[i][0], i, 0});
        }

        int count = 0;
        int smallestElement = 0;
        while(!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            smallestElement = current[0];
            count++;

            if (count == k) {
                break;
            }

            int rowIdx = current[1];
            int colIdx = current[2];
            // if there are more elements in the current row
            if (colIdx + 1 < matrix[rowIdx].length) {
                minHeap.offer(new int[] {matrix[rowIdx][colIdx + 1], rowIdx, colIdx + 1});
            }
        }

        return smallestElement;
    }
}

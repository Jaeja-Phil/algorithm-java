package educative.grokking_coding_interview_patterns_in_java.k_way_merge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given two lists, and an integer 'k', find 'k' pairs of numbers with
 * the smallest sum so that in each pair, each list contributes one number to the pair.
 *
 * constraints:
 * - input lists are sorted in ascending order
 * - if the value of 'k' exceeds the total number of valid pairs that
 *   may be formed, return all pairs.
 *
 * ex:
 * input:
 *   k = 3
 *   list1 = [2, 8, 9]
 *   list2 = [1, 3, 6]
 * output:
 *   [2, 1], [2, 3], [2, 6]
 */
public class $03_FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        int[] list1 = new int[]{2, 8, 9};
        int[] list2 = new int[]{1, 3, 6};
        int target = 3;
        List<List<Integer>> result = solution(list1, list2, target);
        System.out.println(result); // [[2, 1], [2, 3], [2, 6]]
    }

    public static List<List<Integer>> solution(int[] list1, int[] list2, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // create a minHeap
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.sum - b.sum);

        // iterate over list1
        int list1Length = list1.length;
        for (int i = 0; i < Math.min(target, list1Length); i++) {
            // add the first element of list1 and list2 to the minHeap
            minHeap.add(new Pair(list1[i] + list2[0], i, 0));
        }

        int count = 1;
        // while minHeap is not empty (addable elements exist) and count is less than or equal to target
        while (!minHeap.isEmpty() && count <= target) {
            // pull out the top element from the minHeap (which has the smallest sum)
            Pair pair = minHeap.poll();
            int i = pair.i;
            int j = pair.j;

            // add the pair to the result
            result.add(List.of(list1[i], list2[j]));

            // increment count since we added a pair to the result
            count++;

            // if j is less than list2.length - 1, add the next element of list2 to the minHeap
            if (j < list2.length - 1) {
                minHeap.add(new Pair(list1[i] + list2[j + 1], i, j + 1));
            }
        }

        return result;
    }

    private static class Pair {
        int sum;
        int i;
        int j;

        public Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }
}

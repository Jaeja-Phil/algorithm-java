package educative.grokking_coding_interview_patterns_in_java.k_way_merge;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given m numbers of sorted lists in ascending order and integer k, find the kth smallest number
 * among all the given lists.
 *
 * constraints:
 * - if k is greater than the total number of elements in the input lists, return the greatest
 *   element from all the lists.
 * - if there are no elements in the input lists, return 0;
 *
 * ex:
 * input:
 *   lists = [
 *     [2, 6, 8],
 *     [3, 6, 10],
 *     [5, 8, 11]
 *   ]
 *   k = 5
 * output:
 *   [2, 3, 5, 6, *6*, 8, 8, 10, 11] --> 5th smallest number is *6*
 */
public class $02_KthSmallestNumberInMSortedLists {
    public static void main(String[] args) {
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(2, 6, 8),
                Arrays.asList(3, 6, 10),
                Arrays.asList(5, 8, 11)
        );
        int k = 5;
        int result = solution(lists, k);
        System.out.println(result); // 6
        System.out.println("------------------------------");
        lists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 15),
                Arrays.asList(10, 11, 12, 13),
                Arrays.asList(5, 10)
        );
        k = 50;
        result = solution(lists, k);
        System.out.println(result); // 15
    }

    public static int solution(List<List<Integer>> lists, int k) {
        // store number of lists
        int listsLength = lists.size();
        // init min-heap to keep track of smallest elements
        PriorityQueue<NumInfo> kthSmallest = new PriorityQueue<>((a, b) -> a.value - b.value);

        // insert all first elements from each list into min-heap
        for (int idx = 0; idx < listsLength; idx++) {
            if (lists.get(idx).isEmpty()) {
                continue;
            }

            int value = lists.get(idx).get(0);
            int orderInList = 0;
            int listIdx = idx;
            kthSmallest.add(new NumInfo(value, listIdx, orderInList));
        }

        int numbersChecked = 0, smallestNumber = 0;
        while (!kthSmallest.isEmpty()) {
            NumInfo smallest = kthSmallest.poll();
            smallestNumber = smallest.value;
            numbersChecked++;

            if (numbersChecked == k) {
                return smallestNumber;
            }

            // if there are more elements in the same list, add next element to min-heap
            if (lists.get(smallest.listIdx).size() > smallest.orderInList + 1) {
                int value = lists.get(smallest.listIdx).get(smallest.orderInList + 1);
                int orderInList = smallest.orderInList + 1;
                int listIdx = smallest.listIdx;
                kthSmallest.add(new NumInfo(value, listIdx, orderInList));
            }
        }

        return smallestNumber;
    }

    private static class NumInfo {
        private int value;
        private int listIdx;
        private int orderInList;

        public NumInfo(int value, int listIdx, int orderInList) {
            this.value = value;
            this.listIdx = listIdx;
            this.orderInList = orderInList;
        }
    }
}

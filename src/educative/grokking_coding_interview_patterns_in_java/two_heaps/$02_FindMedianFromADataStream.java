package educative.grokking_coding_interview_patterns_in_java.two_heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Implement a data structure that’ll store a dynamically growing list of integers and provide
 * access to their median in O(1)
 *
 * constraints:
 * - -10^5 <= num <= 10^5, where num is an integer received from the data stream
 * - there will be at least 1 element in the data structure before the median is computed
 * - at most 5 x 10^4 calls will be made to the function that calculates the median
 *
 * ex:
 * input: [22, 35, 30]
 * output: 30.0
 *
 * ex:
 * input: [22, 35, 30, 25]
 * output: 27.5
 */
public class $02_FindMedianFromADataStream {
    public static void main(String[] args) {
        int[] nums = {35, 22, 30, 25, 1};
        MedianFinder medianOfAges = null;
        for(int i=0; i< nums.length; i++){
            System.out.print(i+1);
            System.out.print(".\tData stream: [");
            medianOfAges = new MedianFinder();
            for(int j=0; j<=i; j++){
                System.out.print(nums[j]);
                if(j != i)
                    System.out.print(", ");
                medianOfAges.addNum(nums[j]);
            }
            System.out.println("]");
            System.out.println("\t\tThe median for the given numbers is: " + medianOfAges.findMedian());
            System.out.println("maxHeap: " + medianOfAges.maxHeapFirstHalf);
            System.out.println("minHeap: " + medianOfAges.minHeapSecondHalf);
            System.out.println("--------------------------------------------------");
        }
    }

    private static class MedianFinder {
        PriorityQueue<Integer> maxHeapFirstHalf = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeapSecondHalf = new PriorityQueue<>();

        public void addNum(int num) {
            /**
             * 1. if first half is empty OR
             * 2. biggest number in first half is bigger than num
             * then add num to first half
             *
             * else, add num to second half
             * (which is when first half is not empty and biggest number in first half is smaller than num)
             */
            if (maxHeapFirstHalf.isEmpty() || maxHeapFirstHalf.peek() >= num) {
                maxHeapFirstHalf.add(num);
            } else {
                minHeapSecondHalf.add(num);
            }

            /**
             * when there is a size difference (1+) between first half and second half
             * then move the biggest number in first half to second half
             *
             * when first half size is smaller than second half size,
             * then move the smallest number in second half to first half
             */
            if (maxHeapFirstHalf.size() > minHeapSecondHalf.size() + 1) {
                minHeapSecondHalf.add(maxHeapFirstHalf.poll());
            } else if (maxHeapFirstHalf.size() < minHeapSecondHalf.size()) {
                maxHeapFirstHalf.add(minHeapSecondHalf.poll());
            }
        }

        public double findMedian() {
            if (maxHeapFirstHalf.isEmpty() && minHeapSecondHalf.isEmpty()) {
                return 0.0;
            }

            if (maxHeapFirstHalf.size() == minHeapSecondHalf.size()) {
                return (maxHeapFirstHalf.peek() + minHeapSecondHalf.peek()) / 2.0;
            }

            return maxHeapFirstHalf.peek();
        }
    }
}

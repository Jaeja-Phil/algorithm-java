package educative.grokking_coding_interview_patterns_in_java.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We are given an array of closed intervals, intervals, where each interval has a start time
 * and an end time.
 * The input array is sorted with respect to the start times of each interval.
 * For example, intervals = [[1, 4], [3, 6], [7, 9]] is sorted in terms of start times 1, 3, and 7.
 *
 * Merge overlapping intervals and return a new output array consisting of
 * only non-overlapping intervals.
 *
 * constraints:
 * - 1 <= intervals.length <= 10^4
 * - intervals[i].length = 2
 * - 0 <= start time <= end time <= 10^4
 *
 * ex:
 * input: intervals = [[1, 5], [3, 7], [4, 6], [6, 8]]
 * output: [[1, 8]]
 *
 * ex:
 * input: intervals = [[1, 6], [2, 4]]
 * output: [[1, 6]]
 *
 * ex:
 * input: intervals = [[2, 4], [3, 5], [4, 5], [6, 10], [12, 14]]
 * output: [[2, 5], [6, 10], [12, 14]]
 */
public class $01_MergeIntervals {
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 5));
        list.add(new Interval(3, 7));
        list.add(new Interval(4, 6));
        list.add(new Interval(6, 8));
        List<Interval> res = solution(list);
        System.out.println(res); // [[1, 8]]

        list = new ArrayList<>();
        list.add(new Interval(1, 6));
        list.add(new Interval(2, 4));
        res = solution(list);
        System.out.println(res); // [[1, 6]]

        list = new ArrayList<>();
        list.add(new Interval(2, 4));
        list.add(new Interval(3, 5));
        list.add(new Interval(4, 5));
        list.add(new Interval(6, 10));
        list.add(new Interval(12, 14));
        res = solution(list);
        System.out.println(res); // [[2, 5], [6, 10], [12, 14]]
    }

    public static List<Interval> solution(List<Interval> intervals) {
        // ascending sort by interval's start time (first index)
        intervals.sort((a, b) -> Integer.compare(a.getStart(), b.getStart()));
        Interval currentMergedInterval = intervals.get(0);
        List<Interval> answer = new ArrayList<>(intervals.size()); // max size of answer is intervals.size()

        // iterate through the intervals
        for (int i = 1; i < intervals.size(); i++) {
            Interval currentInterval = intervals.get(i);

            // if current interval's start time hasn't passed last interval's end time, merge them
            if (currentMergedInterval.getEnd() >= currentInterval.getStart()) {
                currentMergedInterval.setEnd(Math.max(currentMergedInterval.getEnd(), currentInterval.getEnd()));
            } else {
                // add currentMergedInterval to answer and update last interval
                answer.add(currentMergedInterval);
                currentMergedInterval = currentInterval;
            }
        }

        // add final currentMergedInterval to the answer
        answer.add(currentMergedInterval);

        return answer;
    }

    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}

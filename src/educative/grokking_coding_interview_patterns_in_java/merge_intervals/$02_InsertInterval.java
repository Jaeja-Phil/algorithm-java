package educative.grokking_coding_interview_patterns_in_java.merge_intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of non-overlapping intervals, insert a new interval at the correct position.
 * Each interval is a pair of non-negative numbers, first being the start time and second being the end time.
 * The input list is sorted by start time (ascending)
 *
 * constraints:
 * - 0 <= existingIntervals.length <= 10^4
 * - existingIntervals[i].length == newInterval.length == 2
 * - first number is always less than the second number in each interval
 * - list of intervals is sorted in ascending order based on the first element in every interval
 *
 * ex:
 * input:
 *   existingIntervals = [[1, 3], [5, 7], [8, 9], [10, 13]]
 *   newInterval = [2, 6]
 * output:
 *   [[1, 7], [8, 9], [10, 13]]
 */
public class $02_InsertInterval {
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(5, 7));
        list.add(new Interval(8, 9));
        list.add(new Interval(10, 13));
        Interval newInterval = new Interval(2, 6);
        List<Interval> res = solution(list, newInterval);
        System.out.println(res); // [[1, 7], [8, 9], [10, 13]]
    }

    public static List<Interval> solution(List<Interval> existingIntervals, Interval newInterval) {
        // since constraint states that existing interval's length can be as low as 0...
        if (existingIntervals.size() == 0) {
            return List.of(newInterval);
        }

        /**
         * initialize answer list to a size of existingIntervals + 1
         * why?
         * - new interval may not overlap, and existing intervals may also not overlap making max size
         *   existingIntervals.size() + 1
         * - to reduce # of resizing
         */
        List<Interval> answer = new ArrayList<>(existingIntervals.size() + 1);
        int i = 0;
        int start = newInterval.start;
        int end = newInterval.end;

        // add all intervals that end before new interval start
        while (i < existingIntervals.size() && existingIntervals.get(i).end < start) {
            answer.add(existingIntervals.get(i++));
        }

        // merge new interval with overlapping intervals
        while (i < existingIntervals.size() && existingIntervals.get(i).start <= end) {
            start = Math.min(start, existingIntervals.get(i).start);
            end = Math.max(end, existingIntervals.get(i).end);
            i++;
        }

        // add merged interval
        answer.add(new Interval(start, end));

        // add all remaining intervals
        while (i < existingIntervals.size()) {
            answer.add(existingIntervals.get(i++));
        }

        // finally, return answer
        return answer;
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}

package educative.grokking_coding_interview_patterns_in_java.merge_intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * For two lists of closed intervals given as input, intervalListA and intervalListB,
 * where each interval has its own start and end time, write a function that returns
 * the intersection of the two interval lists.
 *
 * constraints:
 * - 0 <= intervalListA.length, intervalListB.length <= 1000
 * - 0 <= start[i] < end[i] <= 10^9
 * - end[i] < start[i + 1]
 * - 0 <= start[j] < end[j] <= 10^9
 * - end[j] < start[j + 1]
 *
 * ex:
 * intervalListA = [[1, 4], [5, 6], [7, 9]]
 * intervalListB = [[3, 5], [6, 7], [8, 9]]
 * output: [[3, 4], [5, 5], [6, 6], [7, 7], [8, 9]]
 */
public class $03_IntervalListIntersections {
    public static void main(String[] args) {
        List<Interval> intervalListA = new ArrayList<>();
        intervalListA.add(new Interval(1, 4));
        intervalListA.add(new Interval(5, 6));
        intervalListA.add(new Interval(7, 9));
        List<Interval> intervalListB = new ArrayList<>();
        intervalListB.add(new Interval(3, 5));
        intervalListB.add(new Interval(6, 7));
        intervalListB.add(new Interval(8, 9));
        List<Interval> res = solution(intervalListA, intervalListB);
        System.out.println(res); // [[3, 4], [5, 5], [6, 6], [7, 7], [8, 9]]
    }

    public static List<Interval> solution(List<Interval> intervalListA, List<Interval> intervalListB) {
        // initialize two pointers for intervalListA and intervalListB respectively
        int i = 0, j = 0;
        // initialize an answer array
        List<Interval> answer = new ArrayList<>();
        // while both pointers are within their respective interval lists
        while (i < intervalListA.size() && j < intervalListB.size()) {
            Interval intervalA = intervalListA.get(i);
            Interval intervalB = intervalListB.get(j);
            // if intervalA is before intervalB
            if (intervalA.end < intervalB.start) {
                // move intervalA to the next interval
                i++;
                // if intervalB is before intervalA
            } else if (intervalB.end < intervalA.start) {
                // move intervalB to the next interval
                j++;
            } else {
                // if intervalA and intervalB overlap,
                // add the intersection to the answer
                int overlapStart = Math.max(intervalA.start, intervalB.start);
                int overlapEnd = Math.min(intervalA.end, intervalB.end);
                answer.add(new Interval(overlapStart, overlapEnd));
                // move the interval that ends first to the next interval
                if (intervalA.end < intervalB.end) {
                    i++;
                } else {
                    j++;
                }
            }
        }

        // finally, return the answer
        return answer;
    }

    private static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}

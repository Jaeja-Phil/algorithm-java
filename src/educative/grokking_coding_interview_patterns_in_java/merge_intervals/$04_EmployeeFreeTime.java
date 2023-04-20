package educative.grokking_coding_interview_patterns_in_java.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You’re given a list containing the schedules of multiple people.
 * Each person’s schedule is a list of non-overlapping intervals in sorted order.
 * An interval is specified with the start time and the end time, both being positive integers.
 * Your task is to find the list of intervals representing the free time for all the people.
 * We’re not interested in the interval from negative infinity to zero or from the end of the
 * last scheduled interval in the input to positive infinity.
 *
 * constraints:
 * - 1 <= schedule.length, schedule[i].length <= 50
 * - 0 <= interval.start < interval.end <= 10^8
 * - every interval is guaranteed to have non-zero duration
 *
 * ex:
 * input: schedules = [[[1, 2], [5, 6]], [[1, 3]], [[4, 10]]]
 * output: [[3, 4]]
 *
 * ex:
 * input: schedules = [[[2, 3], [7, 9]], [[1, 4], [6, 7]]]
 * output: [[4, 6]]
 */
public class $04_EmployeeFreeTime {
    public static void main(String[] args) {
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 2), new Interval(5, 6)));
        schedule.add(Arrays.asList(new Interval(1, 3)));
        schedule.add(Arrays.asList(new Interval(4, 10)));
        List<Interval> res = solution(schedule);
        System.out.println(res); // [[3, 4]]

        schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(2, 3), new Interval(7, 9)));
        schedule.add(Arrays.asList(new Interval(1, 4), new Interval(6, 7)));
        res = solution(schedule);
        System.out.println(res); // [[4, 6]]
    }

    public static List<Interval> solution(List<List<Interval>> schedules) {
        // initialize intervals to store all employee schedules
        List<Interval> intervals = new ArrayList<>();

        // add all employee schedules
        for (List<Interval> schedule : schedules) {
            intervals.addAll(schedule);
        }

        // sort by start time
        intervals.sort((a, b) -> a.start - b.start);

        // initialize answer list
        List<Interval> freeTime = new ArrayList<>();

        // get max end time so far
        int end = intervals.get(0).end;
        // iterate through all schedules (except first one, since we already have max end time)
        for (int i = 1; i < intervals.size(); i++) {
            // get current schedule
            Interval currentSchedule = intervals.get(i);
            // if current schedule start time is greater than max end time so far
            if (end < currentSchedule.start) {
                // add free time interval
                freeTime.add(new Interval(end, currentSchedule.start));
            }
            // update max end time so far
            end = Math.max(end, currentSchedule.end);
        }

        // return answer list
        return freeTime;
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

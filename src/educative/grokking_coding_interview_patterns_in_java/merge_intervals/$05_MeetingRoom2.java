package educative.grokking_coding_interview_patterns_in_java.merge_intervals;

import java.util.*;

/**
 * We are given an input array of meeting time intervals, intervals, where each interval
 * has a start time and an end time.
 * Your task is to find the minimum number of meeting rooms required to hold these meetings.
 *
 * constraints:
 * - 1 <= intervals.length <= 10^4
 * - 0 <= start I <= end I <= 10^6
 *
 * ex:
 * input: intervals = [[2, 8], [3, 4], [3, 9], [5, 11], [8, 20], [11, 15]]
 * output: 3
 */
public class $05_MeetingRoom2 {
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 8));
        list.add(new Interval(3, 4));
        list.add(new Interval(3, 9));
        list.add(new Interval(5, 11));
        list.add(new Interval(8, 20));
        list.add(new Interval(11, 15));
        int res = solution(list);
        System.out.println(res); // 3

        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(1, 7));
        list2.add(new Interval(2, 6));
        list2.add(new Interval(3, 7));
        list2.add(new Interval(4, 8));
        list2.add(new Interval(5, 8));
        list2.add(new Interval(2, 9));
        list2.add(new Interval(1, 8));
        int res2 = solution(list2);
        System.out.println(res2); // 7
    }

    public static int solution(List<Interval> intervals) {
        // sort intervals by start time
        intervals.sort((a, b) -> a.start - b.start);

        // initialize variable to store minimum room count over iteration
        int minRoomCount = 0;
        // create a minHeap which stores intervals sorted by end time ascending
        // why end time?
        // - room with earliest end time will end the quickest, thus be available for polling the quickest.
        PriorityQueue<Interval> rooms = new PriorityQueue<>((a, b) -> a.end - b.end);

        // iterate over intervals
        for (int i = 0; i < intervals.size(); i++) {
            // get current interval
            Interval currentInterval = intervals.get(i);
            // add currentInterval to the rooms
            rooms.add(currentInterval);
            // remove all intervals that passed current interval's start time
            while (currentInterval.start >= rooms.peek().end) {
                rooms.poll();
            }
            // update minRoomCount
            minRoomCount = Math.max(minRoomCount, rooms.size());
        }

        // finally, return minRoomCount
        return minRoomCount;
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

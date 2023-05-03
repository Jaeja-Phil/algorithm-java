package educative.grokking_coding_interview_patterns_in_java.two_heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a set of 'n' number of tasks, implement a task scheduler method, tasks(), to run in O(n log n)
 * time that finds the minimum number of machines required to complete these 'n' tasks.
 *
 * constraints:
 * - task start time <= task end time
 *
 * ex:
 * input:
 *   tasks = [[1, 7], [8, 13], [5, 6], [10, 14], [6, 7]]
 * output:
 *   2
 */
public class $04_ScheduleTasksOnMinimumMachines {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> tasks = new ArrayList<>();
        tasks.add(new ArrayList<>(List.of(1, 7)));
        tasks.add(new ArrayList<>(List.of(8, 13)));
        tasks.add(new ArrayList<>(List.of(5, 6)));
        tasks.add(new ArrayList<>(List.of(10, 14)));
        tasks.add(new ArrayList<>(List.of(6, 7)));

        System.out.println(solution(tasks)); // 2
    }

    public static int solution(ArrayList<ArrayList<Integer>> tasks) {
        int minMachineCount = 0;
        // sort tasks by start time
        tasks.sort((a, b) -> a.get(0) - b.get(0));

        // create a min heap to store machines
        PriorityQueue<int[]> machinesInUse = new PriorityQueue<>((a, b) -> a[1] - b[1]); // sort by end time

        for (ArrayList<Integer> task : tasks) {
            int taskStartTime = task.get(0);
            int taskEndTime = task.get(1);

            // remove machines that are no longer in use
            while (!machinesInUse.isEmpty() && machinesInUse.peek()[1] <= taskStartTime) {
                machinesInUse.poll();
            }

            // add a new machine to the heap
            machinesInUse.add(new int[]{taskStartTime, taskEndTime});

            // update minMachineCount
            minMachineCount = Math.max(minMachineCount, machinesInUse.size());
        }

        return minMachineCount;
    }
}

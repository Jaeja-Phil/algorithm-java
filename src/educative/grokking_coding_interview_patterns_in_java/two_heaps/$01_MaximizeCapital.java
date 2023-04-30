package educative.grokking_coding_interview_patterns_in_java.two_heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * You need to develop a program for making automatic investment decisions for a busy investor.
 * The investor has some start-up capital, c, to invest and a portfolio of projects in which
 * they would like to invest in.
 * The investor wants to maximize their cumulative capital as a result of this investment.
 *
 * As a basic risk-mitigation measure, the investor would like to set a limit on the number
 * of projects, k, they invest in.
 *
 * constraints:
 * - 1 <= k <= 100000
 * - 0 <= c <= 10^9
 * - 1 <= profits.length <= 10^5
 * - profits.length == capitals.length
 * - 0 <= profits[i] <= 10^4
 * - 0 <= capitals[i] <= 10^9
 *
 * ex:
 * input:
 * k = 2
 * c = 1
 * capitals = [1, 2, 2, 3]
 * profits = [2, 4, 6, 8]
 * output: 11
 */
public class $01_MaximizeCapital {
    public static void main(String[] args) {
        int c = 1, k = 2;
        int[] capitals = {1, 2, 2, 3}, profits = {2, 4, 6, 8};
        System.out.println(solution(c, k, capitals, profits)); // 11
    }

    public static int solution(int c, int k, int[] capitals, int[] profits) {
        int n = capitals.length;
        int currentCapital = c;
        PriorityQueue<int[]> capitalMinHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        /**
         * put all capitals into min-heap which will help us select the project with the smallest capital requirement
         * - index 0: capital
         * - index 1: index of the project
         */
        for (int i = 0; i < n; i++) {
            capitalMinHeap.offer(new int[] {capitals[i], i});
        }

        /**
         * create a max-heap to store all the available projects
         */
        PriorityQueue<int[]> profitsMaxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // variable for storing number of projects selected
        int selectedProjectsCount = 0;
        // while we haven't selected k projects yet...
        while (selectedProjectsCount < k) {
            /**
             * find all projects that can be selected within the available capital
             * and insert them in the max-heap
             */
            while (!capitalMinHeap.isEmpty() && capitalMinHeap.peek()[0] <= currentCapital) {
                int[] j = capitalMinHeap.poll();
                profitsMaxHeap.offer(new int[] {profits[j[1]], j[1]});
            }

            // terminate if we are not able to find any project that can be completed within the available capital
            if (profitsMaxHeap.isEmpty()) {
                break;
            }

            // select the max profit project and update the current capital
            int capital = profitsMaxHeap.poll()[0];
            currentCapital += capital;
            // increment number of projects selected
            selectedProjectsCount++;
        }

        return currentCapital;
    }
}

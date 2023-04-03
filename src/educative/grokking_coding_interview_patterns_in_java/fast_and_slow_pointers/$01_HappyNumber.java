package educative.grokking_coding_interview_patterns_in_java.fast_and_slow_pointers;

/**
 * Write a function to determine if a number is happy.
 * A happy number is a number defined by the following process:
 * - Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * - Repeat the process until the number equals 1 (where it will stay), or it loops
 *   endlessly in a cycle which does not include 1.
 * - Those numbers for which this process ends in 1 are happy.
 *
 * constraints:
 *   - 1 <= n <= 2^31 - 1
 *
 * ex)
 * n = 23
 * 2^2 + 3^2 = 13
 * 1^2 + 3^2 = 10
 * 1^2 + 0^2 = 1
 * - 23 is a happy number
 *
 * ex)
 * n = 12
 * 1^2 + 2^2 = 5
 * 5^2 = 25
 * 2^2 + 5^2 = 29
 * 2^2 + 9^2 = 85
 * 8^2 + 5^2 = 89
 * 8^2 + 9^2 = 145
 * 1^2 + 4^2 + 5^2 = 42
 * 4^2 + 2^2 = 20
 * 2^2 + 0^2 = 4
 * 4^2 = 16
 * 1^2 + 6^2 = 37
 * 3^2 + 7^2 = 58
 * 5^2 + 8^2 = 89 <-- cycle
 * - 12 is not a happy number
 */
public class $01_HappyNumber {
    public static void main(String[] args) {
        boolean res = solution(23);
        System.out.println(res); // true

        res = solution(12);
        System.out.println(res); // false
    }

    public static boolean solution(int n) {
        // initialize two pointers
        int slow = n, fast = n;

        // while the fast pointer is not 1
        // fast pointer, if reached 1, will stay at 1 forever (1^2 = 1)
        while (fast != 1) {
            // move the slow pointer one step
            slow = getNext(slow);
            // move the fast pointer two steps
            fast = getNext(getNext(fast));

            // if the two pointers meet, then there is a cycle
            if (slow == fast) {
                break;
            }
        }

        // if the fast pointer is 1, then the number is happy
        return fast == 1;
    }

    private static int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}

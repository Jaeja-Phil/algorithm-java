package educative.grokking_coding_interview_patterns_in_java.two_pointers;

/**
 * Write a function that takes a string as input and checks whether it can be
 * a valid palindrome by removing at most one character from it.
 *
 * constraints:
 * - 1 <= string.length() <= 10^5
 * - string only consists of English letters
 *
 * ex:
 * - "abceba" --> true
 *   - removing 'e' makes it a palindrome
 */
public class $04_ValidPalindrome2 {
    public static void main(String[] args) {
        boolean res = solution("abceba");
        System.out.println(res); // true

        res = solution("madame");
        System.out.println(res); // true

        res = solution("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga");
        System.out.println(res); // true
    }

    public static boolean solution(String str) {
        // initialize two pointers
        int left = 0, right = str.length() - 1;

        // while two pointers haven't crossed each other
        while (left < right) {
            // check if the characters at the two pointers are the same
            // if so, increment left and decrement right
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                // if not, check if the string is a palindrome by removing the character at the left pointer
                // or the character at the right pointer
                return isPalindrome(str, left + 1, right) || isPalindrome(str, left, right - 1);
            }
        }

        return true;
    }

    private static boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

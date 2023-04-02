package educative.grokking_coding_interview_patterns_in_java.two_pointers;

/**
 * Write a function that takes a string s as input and checks whether it’s a palindrome or not.
 */
public class $01_ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(solution("abba")); // true
        System.out.println(solution("abbaa")); // false
        System.out.println(solution("abbba")); // true
        System.out.println(solution("abbca")); // false

    }

    public static boolean solution(String str) {
        // create 2 pointers, left and right
        // left starts at 0, right starts at the end of the string
        int left = 0, right = str.length() - 1;

        // while left is less than right
        while (left < right) {
            // if the characters at left and right are not equal return false
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
            // NOTE: left is incremented and right is decremented from above if statement
        }

        // if we get here, the string is a palindrome
        return true;
    }
}

package educative.grokking_coding_interview_patterns_in_java.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, inputString find the longest substring without repeating characters,
 * and return the length of that longest substring.
 *
 * constraints:
 * - 1 <= inputString.length <= 50000
 * - inputString consists of English letters, digits, symbols and spaces.
 *
 * ex)
 * input: "bbbbbb"
 * output: 1
 *
 * ex)
 * input: "abcabcbb"
 * output: 3
 * explanation: "abc"
 */
public class $05_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(solution("bbbbbb")); // 1
        System.out.println(solution("abcabcbb")); // 3
    }

    public static int solution(String inputString) {
        // initialize left and right pointers for sliding window
        int left = 0, right = 0;
        // initialize a variable to store the max length, start with 0 since its before traversing
        int maxLength = 0;
        // initialize a map to store the count of each character in the sliding window
        Map<Character, Integer> charCountMap = new HashMap<>();
        // expand the sliding window
        while (right < inputString.length()) {
            // pull out the right character and slide the right side of the sliding window
            char rightChar = inputString.charAt(right++);
            // apply the count of the right character to the sliding window map
            charCountMap.put(rightChar, charCountMap.getOrDefault(rightChar, 0) + 1);
            // shrink left side of the sliding window if the right character's count is greater than 1
            while (charCountMap.get(rightChar) > 1) {
                // pull out the left character and slide the left side of the sliding window
                char leftChar = inputString.charAt(left++);
                // remove the count of the left character from the sliding window map
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
            }
            // update the max length
            // not right - left + 1 because right is already incremented
            maxLength = Math.max(maxLength, right - left);
        }

        // return the max length
        return maxLength;
    }
}

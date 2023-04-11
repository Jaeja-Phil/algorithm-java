package educative.grokking_coding_interview_patterns_in_java.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings—s and t, find the smallest window substring of t.
 * The smallest window substring is the shortest sequence of characters in s that includes
 * all of the characters present in t.
 * The frequency of each character in this sequence should be greater than or equal to the
 * frequency of each character in t.
 * The order of the characters doesn’t matter here.
 *
 * constraints:
 * - s and t consists of lower and uppercase letters
 * - t.length <= s.length
 * - 1 <= s.length
 * - t.length <= 1000
 *
 * ex)
 * s = "ABAACBBA"
 * t = "ABC"
 * output = "ACB"
 */
public class $04_MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(solution("ABAACBBA", "ABC")); // "ACB"
    }

    public static String solution(String s, String t) {
        // since t's constraint states that t can be length of 0, add an early return
        if (t.isEmpty()) {
            return "";
        }

        // initialize left and right pointers for sliding window
        int left = 0, right = 0;
        // initialize result string with empty string
        String result = "";
        // initialize a map to store the count of each character in t
        Map<Character, Integer> charCountMap = new HashMap<>();
        // populate the map with the count of each character in t
        for (char c : t.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        // initialize a map to store the count of each character in the sliding window
        Map<Character, Integer> windowCharCountMap = new HashMap<>();
        // initialize a variable to store required match count
        int required = charCountMap.size();
        // initialize a variable to store current match count
        int current = 0;

        // expand the sliding window
        while (right < s.length()) {
            // pull out the right character
            char rightChar = s.charAt(right++);
            // if the right character is in the map...
            if (charCountMap.containsKey(rightChar)) {
                // apply it to the sliding window map
                windowCharCountMap.put(rightChar, windowCharCountMap.getOrDefault(rightChar, 0) + 1);
                // once the count of the right character in the sliding window map matches the count,
                // increment the current match count
                if (windowCharCountMap.get(rightChar).equals(charCountMap.get(rightChar))) {
                    current++;
                }

                // when the current match count matches the required match count, slide the left side of the window
                while (current == required) {
                    // pull out the left character
                    char leftChar = s.charAt(left++);
                    // if the left character is in the map...
                    if (charCountMap.containsKey(leftChar)) {
                        // decrement the count of the left character in the sliding window map
                        // if the count of the left character in the sliding window map matches the count of the left character in the map,
                        if (windowCharCountMap.get(leftChar).equals(charCountMap.get(leftChar))) {
                            current--;
                        }
                        windowCharCountMap.put(leftChar, windowCharCountMap.get(leftChar) - 1);
                    }
                    // if the result is empty (never set)
                    // or the length of the current window is shorter than the result,
                    if (result.isEmpty() || right - left + 1 < result.length()) {
                        // update the result
                        result = s.substring(left - 1, right);
                    }
                }
            }
        }

        // return result
        return result;
    }
}

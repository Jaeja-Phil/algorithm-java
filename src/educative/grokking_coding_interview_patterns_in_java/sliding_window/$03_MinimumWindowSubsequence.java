package educative.grokking_coding_interview_patterns_in_java.sliding_window;

/**
 * Given strings str1 and str2, find the minimum (contiguous) substring subStr of str1,
 * such that every character of str2 appears in subStr in the same order as it is present in str2.
 * - If there is no such substring, return an empty string.
 * - If there are multiple such substrings, return the one that occurs first.
 *
 * constraints:
 * - 1 <= str1.length <= 2000
 * - 1 <= str2.length <= 100
 *
 * ex)
 * str1 = "abcdebdde"
 * str2 = "bde"
 * output = "bcde"
 */
public class $03_MinimumWindowSubsequence {
    public static void main(String[] args) {
        System.out.println(solution("abcdebdde", "bde")); // "bcde"
        System.out.println(solution("fgrqsqsnodwmxzkzxwqegkndaa", "kzed")); // "kzxwqegknd"
    }

    public static String solution(String str1, String str2) {
        // TODO: review later...
        return "";
    }
}

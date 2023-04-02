package educative.grokking_coding_interview_patterns_in_java.two_pointers;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given a sentence, reverse the order of its words without affecting the order of letters within a given word.
 *
 * constraints
 * - length of sentence is between 1 and 10000
 * - order of the letters within a word is not changed
 *
 * ex) "Hello World" --> "World Hello"
 */
public class $03_ReverseWordsInAString {
    public static void main(String[] args) {
        String[] inputs = {"Hello World!",
                "We love Python.",
                "The quick brown fox jumped over the lazy dog.",
                "Hey!",
                "To be, or not to be",
                "AAAAA"};
        for (String input : inputs) {
            System.out.println(solution(input));
        }
    }

    /**
     * Character approach
     */
    public static String solution(String sentence) {
        char[] chars = sentence.toCharArray();
        int start = 0;
        int end = chars.length - 1;

        // reverse the whole sentence
        reverse(chars, start, end);

        start = 0;
        end = 0;
        // increment end until the end of the sentence
        while (end < chars.length) {
            // when blank space is found, reverse the word
            if (chars[end] == ' ') {
                // end - 1 because the end is pointing to the blank space
                reverse(chars, start, end - 1);
                // set start to the next index of the blank space
                start = end + 1;
            }
            // increment end
            end++;
        }

        // reverse the last word
        reverse(chars, start, end - 1);

        return new String(chars);
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * String approach
     */
    public static String solution2(String sentence) {
        String[] words = sentence.split(" ");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}

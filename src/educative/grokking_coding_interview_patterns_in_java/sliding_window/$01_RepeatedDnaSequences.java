package educative.grokking_coding_interview_patterns_in_java.sliding_window;

import java.util.*;

/**
 * Given a string s that represents a DNA sequence, and a number k, return all the
 * contiguous sequences (substrings) of length k that occur more than once in the string.
 * The order of the returned subsequences does not matter.
 * If no repeated subsequence is found, the function should return an empty set.
 *
 * ex)
 * s = AGACCTAGAC
 * k = 3
 * output = [AGA, GAC]
 *
 * ex)
 * s = AAAAACCCCCAAAAACCCCCC
 * k = 8
 * output = [AAAAACCC, AAACCCCC, AAAACCCC]
 */
public class $01_RepeatedDnaSequences {
    public static void main(String[] args) {
        System.out.println(solution("AGACCTAGAC", 3)); // [AGA, GAC]
        System.out.println(solution("AAAAACCCCCAAAAACCCCCC", 8)); // [AAAAACCC, AAACCCCC, AAAACCCC]
    }

    private static String[] DNA_SEQUENCES = {"A", "T", "C", "G"};

    public static List<String> solution(String s, int k) {
        /**
         * Content of seen & repeated:
         * ex) ACA --> A2T0C1G0
         */
        // To store the unique DNA sequences
        Set<String> seen = new HashSet<>();
        // To store the repeated DNA sequences
        Set<String> repeated = new HashSet<>();
        // To store the count of each DNA sequence
        Map<Character, Integer> countMap = new HashMap<>();
        // Initialize the countMap
        for (String dnaSequence : DNA_SEQUENCES) {
            countMap.put(dnaSequence.charAt(0), 0);
        }

        // left & right are the pointers of the sliding window
        int left = 0, right = 0;
        // Expand the sliding window
        while (right < s.length()) {
            // Add the right character to the countMap
            char rightChar = s.charAt(right);
            countMap.put(rightChar, countMap.get(rightChar) + 1);
            right++;

            /**
             *  Once the sliding window reaches the size of k, we need to
             *  - add the current DNA sequence to the seen set
             *  - add the current DNA sequence to the repeated set if it is already in the seen set
             *  - shrink it (left++)
             */
            if (right - left == k) {
                StringBuilder sb = new StringBuilder();

                // Create the key for the current DNA sequence
                for (String dnaSequence : DNA_SEQUENCES) {
                    sb.append(dnaSequence).append(countMap.get(dnaSequence.charAt(0)));
                }
                String key = sb.toString();

                // if the current DNA sequence is already in the seen set, add it to the repeated set
                if (seen.contains(key)) {
                    repeated.add(key);
                } else {
                    // otherwise, add it to the seen set
                    seen.add(key);
                }

                // Shrink left side of the sliding window
                char leftChar = s.charAt(left);
                countMap.put(leftChar, countMap.get(leftChar) - 1);
                left++;
            }
        }

        // Convert the repeated set to the answer
        List<String> answer = new ArrayList<>();
        for (String key : repeated) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < key.length(); i += 2) {
                String dnaSequence = key.substring(i, i + 1);
                int count = Integer.parseInt(key.substring(i + 1, i + 2));
                for (int j = 0; j < count; j++) {
                    sb.append(dnaSequence);
                }
            }
            answer.add(sb.toString());
        }

        return answer;
    }
}

package doit.ch3;

import java.util.HashMap;
import java.util.Map;

/**
 * Sliding Window
 *
 * DNA 문자열은 모든 문자열에 등장하는 문자가 A, C, G, T로만 구성되어 있다.
 * 임의의 DNA 문자열과 비밀번호로 사용할 부분 문자열의 길이, 그리고 A, C, G, T가 각각 몇번 이상 등장해야 비밀번호로 사용할 수
 * 있는지 주어졌을 때, 만들 수 있는 비밀번호의 종류의 수를 구하는 프로그램을 작성하시오.
 *
 * ex)
 * DNA 문자열: CCTGGATTG
 * 부분 문자열의 길이: 8
 * 부분 문자열에 포함돼야 할 A, C, G, T의 최소 갯수: {2, 0, 1, 1}
 * answer: 0
 *
 * ex)
 * DNA 문자열: GATA
 * 부분 문자열의 길이: 2
 * 부분 문자열에 포함돼야 할 A, C, G, T의 최소 갯수: {1, 0, 0, 1}
 * answer: 2
 * - AT, TA
 */
public class $009_DnaPassword {
    private static char[] DNA_LETTERS = {'A', 'C', 'G', 'T'};

    public static void main(String[] args) {
        int res = solution("CCTGGATTG", 8, new int[] {2, 0, 1, 1});
        System.out.println(res); // 0

        res = solution("GATA", 2, new int[] {1, 0, 0, 1});
        System.out.println(res); // 2
    }

    public static int solution(String dna, int length, int[] mins) {
        // 찾은 부분 문자열의 갯수가 될 변수 선언
        int count = 0;
        // sliding window 포인터로 사용할 left, right 선언
        int left = 0, right = 0;
        // 각 dna 문자별 현재 count를 기록할 map 선언
        Map<Character, Integer> map = new HashMap<>();

        // right가 끝에 도달할 때 까지
        while (right < dna.length()) {
            // 현재 길이를 구한다 (right - left + 1 으로 현재 window의 길이를 알 수 있음)
            int currentLength = right - left + 1;
            // 현재 오른쪽 문자가 무엇인지 저장해둔다
            char rightChar = dna.charAt(right++);
            // 오른쪽 문자가 window 안에 들어왔으니 해당 문자의 count를 1 더한다
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            // 만약 현재 길이가 부분 문자열의 길이에 도달하였을 경우
            if (currentLength == length) {
                /**
                 * 비밀번호를 만들 수 있는지 체크할 flag를 true로 선언해둔다
                 * true인 이유?
                 * - 최소 count를 만족하지 못하는 문자가 있을 경우 false로 변경하여 break 할 것이며,
                 *   false 로 변경한 것이 없을 경우 비밀번호 화 가능하기 때문
                 */
                boolean passwordable = true;
                // loop를 돌며 각 dna 문자별로 최소 count를 만족하였는지 확인한다
                for (int i = 0; i < mins.length; i++) {
                    int min = mins[i];
                    if (map.getOrDefault(DNA_LETTERS[i], 0) < min) {
                        passwordable = false;
                        break;
                    }
                }
                // 만약 passwordable이 false로 변경되지 않았다면 비밀번호화가 가능한 문자열을 찾았으므로 count를 증가시킨다
                if (passwordable) {
                    count++;
                }
                // 윈도우 좌측을 slide시켜주며 map에 count도 반영한다 (윈도우에서 왼쪽 문자가 빠진다)
                char leftChar = dna.charAt(left++);
                map.put(leftChar, map.get(leftChar) - 1);
            }
        }

        // 찾은 count 개수 반환
        return count;
    }
}

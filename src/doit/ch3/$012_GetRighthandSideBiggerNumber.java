package doit.ch3;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 크기가 N인 수열 A(1), A(2), ... A(N) 이 주어졌을 때, A(i) 보다 오른쪽에 있는 수 중 가장 가까운 큰 수를 찾는 프로그램을 작성하시오.
 * - 더 큰 수가 없을 경우 -1
 *
 * ex)
 * numbers = [3, 5, 2, 7]
 * output = [5, 7, 7, -1]
 */
public class $012_GetRighthandSideBiggerNumber {
    public static void main(String[] args) {
        int[] res = solution(List.of(3, 5, 2, 7));
        System.out.println(Arrays.toString(res)); // [5, 7, 7, -1]

        res = solution(List.of(9, 5, 4, 8));
        System.out.println(Arrays.toString(res)); // [-1, 8, 8, -1]
    }

    public static int[] solution(List<Integer> numbers) {
        // 스택 선언
        Stack<Integer> stack = new Stack<>();
        // 정답을 담을 배열 선언
        int[] answer = new int[numbers.size()];
        // stack에 담길 숫자는 numbers의 index, 따라서 0번째 index를 먼저 넣어두고 시작
        stack.push(0);
        // 1번째 index부터 순회
        for (int i = 1; i < numbers.size(); i++) {
            // 현재 숫자를 구함
            int currentNum = numbers.get(i);
            // 스택이 비어있지 않고, 스택의 마지막 숫자가 현재 숫자보다 작다면
            while (!stack.isEmpty() && numbers.get(stack.peek()) < currentNum) {
                // 스택의 마지막 숫자의 index를 pop하고, 현재 숫자를 answer에 넣어줌
                answer[stack.pop()] = currentNum;
            }
            // 현재 index를 스택에 넣어줌
            stack.push(i);
        }
        // 스택에 남아있는 index들은 더 큰 수가 없으므로 -1을 넣어줌
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        return answer;
    }
}

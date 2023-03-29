package doit.ch3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 임의의 수열을 스택에 넣었다가 출력하는 방식으로 오름차순 수열을 출력할 수 있는지 확인하고, 출력할 수 있다면
 * push와 pop 연산을 어떤 순서로 수행해야 하는지를 알아내는 프로그램을 작성하시오.
 * - 같은 정수는 두번 이상 나오지 않는다.
 * - '+'는 push 연산을 의미하고, '-'는 pop 연산을 의미한다.
 * - 숫자는 1 <= n <= 100000 이다.
 *
 * ex)
 * stack = [4, 3, 6, 8, 7, 5, 2, 1]
 * output = ['+', '+', '+', '+', '-', '-', '+', '+', '-', '+', '+', '-', '-', '-', '-', '-']
 */
public class $011_AscendingStackSequence {
    private static final char PUSH = '+';
    private static final char POP = '-';

    public static void main(String[] args) {
        List<Character> res = solution(List.of(4, 3, 6, 8, 7, 5, 2, 1));
        System.out.println(res); // ['+', '+', '+', '+', '-', '-', '+', '+', '-', '+', '+', '-', '-', '-', '-', '-']

        res = solution(List.of(1, 2, 5, 3, 4));
        System.out.println(res); // []
    }

    public static List<Character> solution(List<Integer> numbers) {
        // 가능하다고 판단되는 순간까지의 push / pop 기록을 남길 answer 선언
        List<Character> answer = new ArrayList<>();
        // 1부터 시작 (1 <= n <= 100000)
        int num = 1;
        // 스택을 구현할 ArrayList 선언
        Stack<Integer> stack = new Stack<>();


        for (int i = 0; i < numbers.size(); i++) {
            // 리스트의 현재 숫자를 구함
            int currentNum = numbers.get(i);
            /**
             * currentNum이 num보다 크거나 같다면
             * - stack에 num을 push하고 1 증가시킴
             * - answer에 push 기록을 남김
             */
            while (num <= currentNum) {
                stack.add(num++);
                answer.add(PUSH);
            }
            /**
             * num이 더 커졌다면, currentNum이 스택의 마지막 숫자와 같은지 확인
             * 같다면 pop 연산을 수행하고 answer에 pop 기록을 남김
             */
            if (stack.peek() == currentNum) {
                stack.pop();
                answer.add(POP);
            } else {
                // currentNum과 스택의 마지막 숫자가 같지 않다면 불가능한 수열이므로 빈 리스트를 반환
                return new ArrayList<>();
            }
        }

        return answer;
    }
}

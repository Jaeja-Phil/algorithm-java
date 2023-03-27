package doit.ch3;

import java.util.*;

/**
 * Sliding Window
 *
 * N개의 수 A(1), A(2), ... A(N) 와 L 이 주어졌을 때,
 * A(i - L + 1) ~ A(i) 중 최솟값을 D라고 할 경우, D 에 저장된 수를 출력하는 프로그램을 작성하시오.
 *
 * ex) numbers: [1, 5, 2, 3, 6, 2, 3, 7, 3, 5, 2, 6]
 *     windowSize: 3
 *     output: [1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 2, 2]
 */
public class $010_FindMin {
    public static void main(String[] args) {
        List<Node> res = solution(List.of(1, 5, 2, 3, 6, 2, 3, 7, 3, 5, 2, 6), 3);
        System.out.println(res); // [1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 2, 2]
    }

    public static List<Node> solution(List<Integer> numbers, int windowSize) {
        // 정답 리스트로 반환할 answer 선언
        List<Node> answer = new ArrayList<>();
        /**
         * loop를 돌며 최솟값을 찾기 위한 Deque 선언
         * deque를 사용하는 이유?
         * - 양쪽에서 데이터를 추가하거나 삭제할 수 있기 때문에, 최솟값을 찾는데 유리하다.
         */
        Deque<Node> deque = new LinkedList<>();

        // loop 시작
        for (int i = 0; i < numbers.size(); i++) {
            // 현재 숫자를 구한다
            int currentNum = numbers.get(i);
            /**
             * deque의 마지막 값이 현재 숫자보다 큰 동안, 마지막 값을 제거한다.
             * 현재 들어갈 값이 더 작다면 최솟값을 구할때 앞선 숫자들을 저장할 필요가 없기 때문
             */
            while (!deque.isEmpty() && deque.peekLast().getVal() > currentNum) {
                deque.removeLast();
            }
            // 현재 숫자를 deque에 추가한다.
            deque.addLast(new Node(i, currentNum));
            // 정답에 첫번째 숫자를 추가한다.
            answer.add(deque.peekFirst());
            // 범위에서 벗어난 숫자를 제거한다.
            if (i - windowSize + 1 >= deque.peekFirst().getIdx()) {
                deque.pollFirst();
            }
        }

        // 정답 반환
        return answer;
    }

    static class Node {
        private final int idx;
        private final int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        public int getIdx() {
            return idx;
        }

        public int getVal() {
            return val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}

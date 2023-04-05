package educative.grokking_coding_interview_patterns_in_java.fast_and_slow_pointers;

/**
 * Given a singly linked list, return the middle node of the linked list.
 * If the number of nodes in the linked list is even, return the second middle node.
 *
 * constraints:
 * - 1 <= number of nodes <= 100
 * - 1 <= value of each node <= 100
 *
 * ex)
 * 1 --> 2 --> 3 --> 4 --> 5 --> null
 * - return 3
 *
 * ex)
 * 1 --> 2 --> 3 --> 4 --> 5 --> 6 --> null
 * - return 4
 */
public class $03_MiddleOfLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(solution(head));

        head.next.next.next.next.next = new ListNode(6);
        System.out.println(solution(head));
    }

    public static ListNode solution(ListNode head) {
        // set up fast and slow pointers
        ListNode slow = head, fast = head;

        // when fast can move two nodes
        while (fast != null && fast.next != null) {
            // move slow one step
            slow = slow.next;
            // move fast two steps
            fast = fast.next.next;
        }

        // slow must be in the middle of list since fast moved 2 steps at a time
        // while slow moved 1 step at a time.
        // for even number of nodes, slow is at second middle node
        return slow;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }

        public String toString() {
            return String.valueOf(this.val);
        }
    }
}

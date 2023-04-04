package educative.grokking_coding_interview_patterns_in_java.fast_and_slow_pointers;

/**
 * Check if a linked list contains a cycle or not. If a cycle exists, return TRUE. Otherwise, return FALSE.
 *
 * constraints:
 * - number of nodes in the list in the range 0 ~ 500
 * - -100000 <= Node.val <= 100000
 */
public class $02_LinkedListCycle {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println(solution(head)); // false

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = head.next.next;
        System.out.println(solution(head)); // true
    }

    public static boolean solution(ListNode head) {
        // set up two pointers
        // slow - moves one step at a time
        // fast - moves two steps at a time
        ListNode slow = head, fast = head;

        // iterate until fast reaches the end of the list
        while (fast != null && fast.next != null) {
            // move slow pointer one step
            slow = slow.next;
            // move fast pointer two steps
            fast = fast.next.next;
            // if slow and fast pointers meet, then there is a cycle
            if (slow == fast) {
                return true;
            }
        }

        // if fast reaches the end of the list, then there is no cycle
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

package educative.grokking_coding_interview_patterns_in_java.fast_and_slow_pointers;

/**
 * For the given head of the linked list, find out if the linked list is a palindrome or not.
 * Return TRUE if the linked list is a palindrome. Otherwise, return FALSE.
 *
 * constraints:
 * - number of nodes in the list in the rang 1 ~ 500
 * - 0 <= Node.val <= 9
 *
 * ex)
 * input: 2 -> 4 -> 6 -> 4 -> 2 -> null
 * output: true
 *
 * ex)
 * input: 2 -> 4 -> 6 -> 4 -> 2 -> 2 -> null
 * output: false
 */
public class $05_PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println(solution(head)); // true

        head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(2);
        System.out.println(solution(head)); // false
    }

    public static boolean solution(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        // find middle node
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse second half
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // compare first half and second half
        ListNode firstHalf = head;
        ListNode secondHalf = prev;
        while (secondHalf != null) {
            if (firstHalf.value != secondHalf.value) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // if all values match, return true
        return true;
    }

    static class ListNode {
        int value = 0;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }
    }
}

/**
 * NOTE: how to reverse linked list
 * ------------------------------------------
 * prev = null
 * curr = head
 * while (curr != null) {
 *    ListNode next = curr.next
 *    curr.next = prev
 *    prev = curr
 *    curr = next
 * }
 * ------------------------------------------
 * prev is now the head of the reversed linked list
 */

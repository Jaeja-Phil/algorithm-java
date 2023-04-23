package educative.grokking_coding_interview_patterns_in_java.in_place_reversal_of_linked_list;

/**
 * Given a linked list, reverse the nodes of the linked list 'k' at a time and return the modified list.
 * Here, 'k' is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of 'k' , the nodes left in the end will remain in their original order.
 *
 * You can’t alter the values of the linked list nodes. Only the nodes themselves may be changed.
 *
 * constraints:
 * - use only O(1) extra memory space
 * - 1 <= k <= n <= 500
 * - 0 <= Node.value <= 1000
 *
 * ex:
 * input: nodes: 6 --> 8 --> 7, k = 1
 * output: 6 --> 8 --> 7
 *
 * ex:
 * input: nodes: 9 --> 0 --> 8 --> 2, k = 2
 * output: 0 --> 9 --> 2 --> 8
 */
public class $02_ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode list = new ListNode(6);
        list.next = new ListNode(8);
        list.next.next = new ListNode(7);
        ListNode res = solution(list, 1);
        while (res != null) {
            System.out.print(res.data + " --> "); // 6 --> 8 --> 7 -->
            res = res.next;
        }
        System.out.println();

        ListNode list2 = new ListNode(9);
        list2.next = new ListNode(0);
        list2.next.next = new ListNode(8);
        list2.next.next.next = new ListNode(2);
        ListNode res2 = solution(list2, 2);
        while (res2 != null) {
            System.out.print(res2.data + " --> "); // 0 --> 9 --> 2 --> 8 -->
            res2 = res2.next;
        }
    }

    public static ListNode solution(ListNode head, int k) {
        if (head == null || head.next ==  null || k == 1) {
            return head;
        }

        // set up temp node in order to return nodes reversed in K group
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // initialize count to keep track of traversed list node count
        int count = 0;
        /**
         * set starting point as dummyHead
         * why?
         * - dummyHead current is pointed to the last head's previous node
         * - reverse needs previous node to reattach previous and next nodes
         */
        ListNode start = dummyHead;

        // iterate while head isn't null
        while (head != null) {
            // increment count
            count++;
            // when count finally reached k groups
            if (count % k == 0) {
                // reverse & reassign
                start = reverse(start, head.next);
                // set head next to start.next which points to the next original node
                head = start.next;
            } else {
                // move head by one node
                head = head.next;
            }
        }

        // finally, return k group reversed node
        return dummyHead.next;
    }

    /**
     * start: previous node of reverse target nodes
     * end: after node of reverse target nodes
     * returns node which is going to be a new starting point for another reverse
     */
    public static ListNode reverse(ListNode start, ListNode end) {
        // set current node to start.next (first node to be reversed)
        ListNode curr = start.next;
        // set previous node to start (previous node of first node to be reversed)
        ListNode prev = start;
        // set head to curr (which, after reverse, will be the last node of reversed nodes)
        ListNode head = curr;

        // iterate while curr isn't end
        while (curr != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // reattach nodes
        start.next = prev;
        head.next = end;

        /**
         *  return head which is the last node of reversed nodes
         *  why?
         *  - this will be the new starting point for another reverse,
         *    which will be the previous node of next reverse target nodes
         */
        return head;
    }

    public static class ListNode {
        public int data;
        public ListNode next;
        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
}

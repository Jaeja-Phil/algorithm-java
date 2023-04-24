package educative.grokking_coding_interview_patterns_in_java.in_place_reversal_of_linked_list;

/**
 * You’re given the head of a singly linked list with n nodes and two positive integers, left and right.
 * Our task is to reverse the list’s nodes from position left to position right and return the reversed list.
 *
 * constraints:
 * - n is number of nodes in a linked list
 * - -5000 <= Node.value <= 5000
 * - left and right are positive integers
 * - 1 <= left <= right <= n
 *
 * ex:
 * input:
 *  left = 1
 *  right = 2
 *  nodes = 6 --> 8 --> 7
 * output:
 *  8 --> 6 --> 7
 */
public class $03_ReverseLinkedList2 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.createLinkedList(new int[]{6, 8, 7});
        LinkedListNode result = solution(1, 2, list.head);
        while (result != null) {
            System.out.print(result.data + " --> ");
            result = result.next;
        }
    }

    public static LinkedListNode solution(int left, int right, LinkedListNode head) {
        // if left is the same as right, no need to reverse, return head
        if (left == right) {
            return head;
        }

        // set up dummyHead which will be a placeholder for head's previous node
        LinkedListNode dummyHead = new LinkedListNode(-5001);
        // set dummyHead's next to head
        dummyHead.next = head;
        // set start pointer to traverse the list
        LinkedListNode start = dummyHead;
        // set n to keep track of traversed list node count
        int n = 0;
        // traverse the list until n is equal to left
        while (n < left) {
            // increment n
            n++;

            // once left is reached
            if (left == n) {
                // reverse the list from left to right
                reverse(start, right - left);
                break;
            }

            // increment start pointer if left is not reached
            start = start.next;
        }

        // return dummyHead's next (which is the new head)
        return dummyHead.next;
    }

    /**
     * start: previous node of the reverse starting point
     * steps: number of nodes to reverse
     */
    private static void reverse(LinkedListNode start, int steps) {
        // set curr to start's next node (which is the reverse starting point)
        LinkedListNode curr = start.next;
        // set prev to null (which will be the new head of the reversed list)
        LinkedListNode prev = null;
        // set head to curr (which will be the new tail of the reversed list)
        LinkedListNode head = curr;
        int currentStep = 0;
        // traverse and reverse nodes until currentStep is equal to steps
        while (currentStep++ <= steps) {
            LinkedListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // reattach the reversed list to the original list
        // start is still pointing to the previous node of the reverse starting point
        // set start's next to prev (which is the new head of the reversed list)
        start.next = prev;
        // head is pointing to the new tail of the reversed list
        // set head's next to curr (which is the next node of the reverse ending point)
        head.next = curr;
    }

    private static class LinkedListNode {
        public int data;
        public LinkedListNode next;
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private static class LinkedList<T> {
        public LinkedListNode head;

        public LinkedList() {
            this.head = null;
        }

        public void insertNodeAtHead(LinkedListNode node) {
            if (this.head == null) {
                this.head = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }

        public void createLinkedList(int[] lst) {
            for (int i = lst.length - 1; i >= 0; i--) {
                LinkedListNode newNode = new LinkedListNode(lst[i]);
                insertNodeAtHead(newNode);
            }
        }
    }
}

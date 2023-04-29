package educative.grokking_coding_interview_patterns_in_java.in_place_reversal_of_linked_list;

/**
 * Given a singly linked list, swap every two adjacent nodes of the linked list.
 * After the swap, return the head of the linked list.
 *
 * constraints:
 * - solve without modifying values in the list's nodes (i.e. only nodes themselves may be changed)
 * - number of nodes in the list in range [0, 100]
 * - 0 <= Node.data <= 100
 *
 * ex:
 * input = 9 > 0 > 8 > 2
 * output = 0 > 9 > 2 > 8
 */
public class $07_SwapNodesInPairs {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.createLinkedList(new int[] {9, 0, 8, 2});
        LinkedListNode res = solution(ll.head);
        while (res != null) {
            System.out.print(res.data + " > "); // 0 > 9 > 2 > 8 >
            res = res.next;
        }
    }

    public static LinkedListNode solution(LinkedListNode head) {
        // set up dummy head, which can be used
        // 1. to return new head of list after swapping
        // 2. to keep track of previous node in list
        LinkedListNode dummyHead = new LinkedListNode(-1);
        dummyHead.next = head;

        LinkedListNode prev = dummyHead;
        LinkedListNode curr = head;
        // while swappable pair exists...
        while (curr != null && curr.next != null) {
            // store 2nd node in pair
            LinkedListNode next = curr.next;
            // make 1st node in pair point to first node in next pair
            curr.next = next.next;
            // make 2nd node in pair point to 1st node in pair
            next.next = curr;
            // make previous pair's last node point to 2nd node in pair (which is now the 1st node in pair)
            prev.next = next;
            // move prev and curr pointers forward
            prev = curr;
            curr = curr.next;
        }

        return dummyHead.next;
    }

    private static class LinkedListNode {
        public int data;
        public LinkedListNode next;
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "LinkedListNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    private static class LinkedList {
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

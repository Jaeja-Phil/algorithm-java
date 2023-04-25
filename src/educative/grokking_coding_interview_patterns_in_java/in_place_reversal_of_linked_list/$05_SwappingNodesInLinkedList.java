package educative.grokking_coding_interview_patterns_in_java.in_place_reversal_of_linked_list;

/**
 * Given the linked list and an integer k, return the head of the linked list after swapping the values of the kth
 * node from the beginning and the kth node from the end of the linked list.
 *
 * constraints:
 * - The number of nodes in the list is n.
 * - 1 <= k <= n <= 500
 * - -5000 <= Node.data <= 5000
 *
 * ex:
 * k = 1
 * input = 6 > 8 > 7
 * output = 7 > 8 > 6
 *
 * ex:
 * k = 2
 * input = 9 > 0 > 8 > 2
 * output = 9 > 8 > 0 > 2
 */
public class $05_SwappingNodesInLinkedList {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(6);
        head.next = new LinkedListNode(8);
        head.next.next = new LinkedListNode(7);
        LinkedListNode res = solution(head, 1);
        while (res != null) {
            System.out.print(res.data + " > "); // 7 > 8 > 6 >
            res = res.next;
        }
    }

    public static LinkedListNode solution(LinkedListNode head, int k) {
        // find k'th node from the start
        LinkedListNode curr = head;
        int idx = 1;
        while (curr != null && idx < k) {
            idx++;
            curr = curr.next;
        }

        // save current node for value swapping later
        LinkedListNode firstKthNode = curr;

        // get last k'th node by incrementing both current node and lastKthNode
        // why does this work?
        // - because curr is already at k'th node from the start, so we just need to increment lastKthNode until
        //   curr reaches the end. then, lastKthNode will be at k'th node from the end
        LinkedListNode lastKthNode = head;
        while (curr != null && curr.next != null) {
            curr = curr.next;
            lastKthNode = lastKthNode.next;
        }

        // swap lastKthNode and firstKthNode's value
        int tmp = lastKthNode.data;
        lastKthNode.data = firstKthNode.data;
        firstKthNode.data = tmp;

        return head;
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

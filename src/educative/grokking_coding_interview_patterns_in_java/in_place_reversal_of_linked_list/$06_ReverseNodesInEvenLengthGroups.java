package educative.grokking_coding_interview_patterns_in_java.in_place_reversal_of_linked_list;

/**
 * You’re given a linked list. Your task is to reverse all of the nodes that are present in
 * the groups with an even number of nodes in them.
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths
 * form the sequence of the natural numbers (1,2,3,4...).
 * The length of a group is the number of nodes assigned to it. In other words:
 * - 1st node is assigned to the 1st group
 * - 2nd and 3rd nodes are assigned to the second group
 * - 4th, 5th, and 6th nodes are assigned to the third group
 *
 * You have to return the head of the resulting linked list.
 *
 * constraints:
 * - length of the last group may be less than or equal to 1 + the length of the second to the last group
 * - 1 <= number of nodes <= 500
 * - 0 <= value of node <= 1000
 *
 * ex:
 * input: 1 > 1 > 0 > 6
 * output: 1 > 0 > 1 > 6
 *
 * ex:
 * input: 1 > 1 > 0 > 6 > 5
 * output: 1 > 0 > 1 > 5 > 6
 *
 * ex:
 * input: 5 > 2 > 6 > 3 > 9 > 1 > 7 > 3 > 8 > 4
 * output: 5 > 6 > 2 > 3 > 9 > 1 > 4 > 8 > 3 > 7
 */
public class $06_ReverseNodesInEvenLengthGroups {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(5);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(6);
        head.next.next.next = new LinkedListNode(3);
        head.next.next.next.next = new LinkedListNode(9);
        head.next.next.next.next.next = new LinkedListNode(1);
        head.next.next.next.next.next.next = new LinkedListNode(7);
        head.next.next.next.next.next.next.next = new LinkedListNode(3);
        head.next.next.next.next.next.next.next.next = new LinkedListNode(8);
        head.next.next.next.next.next.next.next.next.next = new LinkedListNode(4);
        LinkedListNode res = solution(head);
        while (res != null) {
            System.out.print(res.data + " > "); // 5 > 6 > 2 > 3 > 9 > 1 > 4 > 8 > 3 > 7 >
            res = res.next;
        }
    }

    public static LinkedListNode solution(LinkedListNode head) {
        // up to 2 nodes, no need to reverse, return head
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // Node immediately before the current group, which will start from head since first node is a group of one node
        LinkedListNode prev = head;
        int groupSize = 2; // The head doesn't need to be reversed since it's a group of one node, so starts with length 2
        while (prev.next != null) {
            // set node to prev
            LinkedListNode node = prev;
            int n = 0; // length of current group
            for (int i = 0; i < groupSize; i++) {
                // if node.next is null, it means the current group is the last group
                if (node.next == null) {
                    break;
                }
                // increment n and move node to the next node
                n++;
                node = node.next;
            }

            // if length is odd, set prev to node
            if (n % 2 != 0) {
                prev = node;
            } else { // if length is even, reverse the group
                // pointer for next node after the group
                LinkedListNode reverse = node.next;
                // pointer for first node of the group
                LinkedListNode curr = prev.next;
                // reverse process
                for (int j = 0; j < n; j++){
                    LinkedListNode next = curr.next;
                    curr.next = reverse;
                    reverse = curr;
                    curr = next;
                }
                // after reverse, prev.next is now pointing to the last node of the group
                LinkedListNode lastNodeOfGroup = prev.next;
                // set lastNodeOfGroup.next to the next node after the group
                prev.next = node;
                prev = lastNodeOfGroup;
            }

            // increment groupSize
            groupSize++;
        }

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

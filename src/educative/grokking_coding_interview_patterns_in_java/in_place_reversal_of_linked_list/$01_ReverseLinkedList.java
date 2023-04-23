package educative.grokking_coding_interview_patterns_in_java.in_place_reversal_of_linked_list;

import java.util.Arrays;

/**
 * Given the head of a singly linked list, reverse the linked list and return its updated head.
 *
 * constraints:
 * - n is number of nodes in a linked list
 * - 1 <= n <= 500
 * - -5000 <= Node.value <= 5000
 *
 * ex:
 * input: 6 --> 8 --> 7
 * output: 7 --> 8 --> 6
 */
public class $01_ReverseLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.insertNodeAtHead(new LinkedListNode(7));
        list.insertNodeAtHead(new LinkedListNode(8));
        list.insertNodeAtHead(new LinkedListNode(6));
        LinkedListNode res = solution(list.head);
        while (res != null) {
            System.out.print(res.data + " --> ");
            res = res.next;
        }
    }

    public static LinkedListNode solution(LinkedListNode head) {
        /**
         * initialize prev to null
         * prev will be the new head of the reversed linked list after traversal
         */
        LinkedListNode prev = null;

        // while head is not null...
        while (head != null) {
            // store head.next in next
            LinkedListNode next = head.next;
            // set head.next to prev (reverse the link)
            head.next = prev;
            // set prev to current head
            prev = head;
            // set head to next
            head = next;
        }

        return prev;
    }

    public static class LinkedList<T> {
        public LinkedListNode head;
        public LinkedList() {
            this.head = null;
        }
        public void insertNodeAtHead(LinkedListNode node) {
            if (this.head != null) {
                node.next = this.head;
            }
            this.head = node;
        }
    }

    public static class LinkedListNode {
        public int data;
        public LinkedListNode next;
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
}

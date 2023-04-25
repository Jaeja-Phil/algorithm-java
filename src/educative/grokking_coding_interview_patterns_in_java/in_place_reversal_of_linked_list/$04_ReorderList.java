package educative.grokking_coding_interview_patterns_in_java.in_place_reversal_of_linked_list;

/**
 * Given the head of a singly linked list, reorder the list as if it were folded on itself.
 *
 * constraints:
 * - range of number of nodes in the list is [1, 500]
 * - -5000 <= Node.data <= 5000
 *
 * ex:
 * - input: 1 > 2 > 3 > 4 > 5
 * - output: 1 > 5 > 2 > 4 > 3
 */
public class $04_ReorderList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.createLinkedList(new int[] {1, 2, 3, 4, 5});
        LinkedListNode result = solution(list.head);
        while (result != null) {
            System.out.print(result.data + " > "); // 1 > 5 > 2 > 4 > 3 >
            result = result.next;
        }
    }

    public static LinkedListNode solution(LinkedListNode head) {
        // constraint states that min number of nodes is 1
        // when 1 node, return head
        if (head.next == null) {
            return head;
        }

        // find middle of linked list
        LinkedListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse second half of linked list
        slow = reverse(slow);

        LinkedListNode curr = head;

        // reorder linked list
        while (slow != null && slow.next != null) {
            LinkedListNode temp = curr.next;
            curr.next = slow;
            slow = slow.next;
            curr.next.next = temp;
            curr = temp;
        }

        return head;
    }

    private static LinkedListNode reverse(LinkedListNode head) {
        LinkedListNode prev = null;
        while (head != null) {
            LinkedListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    private static class LinkedListNode {
        public int data;
        public LinkedListNode next;
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
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

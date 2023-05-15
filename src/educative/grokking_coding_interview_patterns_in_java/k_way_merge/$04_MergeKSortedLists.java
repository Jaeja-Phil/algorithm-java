package educative.grokking_coding_interview_patterns_in_java.k_way_merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of k sorted linked lists, merge them into a single sorted linked list.
 *
 * constraints:
 * - k = lists.length
 * - 0 <= k <= 10^3
 * - 0 <= lists[i].length <= 500
 * - -10^3 <= lists[i][j] <= 10^3
 * - each lists[i] is sorted in ascending order
 * - the sum of lists[i].length is less than or equal to 10^3
 *
 * ex:
 * input:
 *   lists = [
 *     [2, 4, 6, 8],
 *     [1, 3, 5, 7]
 *   ]
 * output:
 *   [1, 2, 3, 4, 5, 6, 7, 8]
 */
public class $04_MergeKSortedLists {
    public static void main(String[] args) {
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(2, 4, 6, 8),
                Arrays.asList(1, 3, 5, 7)
        );
        List<LinkedList> llist = new ArrayList<>();
        for (List<Integer> list : lists) {
            LinkedList linkedList = new LinkedList();
            linkedList.createLinkedList(list);
            llist.add(linkedList);
        }
        LinkedListNode result = solution(llist);
        while (result != null) {
            System.out.print(result.data + " > "); // 1 > 2 > 3 > 4 > 5 > 6 > 7 > 8 >
            result = result.next;
        }
    }

    public static LinkedListNode solution(List<LinkedList> lists) {
        // validation
        if (lists.size() == 0) {
            return null;
        }

        int step = 1;
        while (step < lists.size()) {
            for (int i = 0; i < lists.size() - step; i += step * 2) {
                lists.get(i).head = mergeTwoLists(lists.get(i).head, lists.get(i + step).head);
            }
            step *= 2;
        }

        return lists.get(0).head;
    }

    private static LinkedListNode mergeTwoLists(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode dummy = new LinkedListNode(-1);
        LinkedListNode prev = dummy;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                prev.next = head1;
                head1 = head1.next;
            } else {
                prev.next = head2;
                head2 = head2.next;
            }
            prev = prev.next;
        }

        if (head1 != null) {
            prev.next = head1;
        } else if (head2 != null) {
            prev.next = head2;
        }

        return dummy.next;
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
        public void createLinkedList(List<Integer> lst) {
            for (int i = lst.size() - 1; i >= 0; i--) {
                LinkedListNode newNode = new LinkedListNode(lst.get(i));
                insertNodeAtHead(newNode);
            }
        }
    }
}

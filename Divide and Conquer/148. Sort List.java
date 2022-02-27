// https://leetcode.com/problems/sort-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;

        ListNode first = sortList(head);
        ListNode second = sortList(temp);
        return merge(first, second);
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode();
        ListNode iter = dummy;

        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                iter.next = node1;
                node1 = node1.next;
            } else {
                iter.next = node2;
                node2 = node2.next;
            }
            iter = iter.next;
        }
        iter.next = node1 != null ? node1 : node2;
        return dummy.next;
    }
}
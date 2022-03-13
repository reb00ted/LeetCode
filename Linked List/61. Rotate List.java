// https://leetcode.com/problems/rotate-list/

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int length = 0;
        ListNode iter = head;
        while (iter != null) {
            iter = iter.next;
            length++;
        }

        k %= length;
        ListNode first = head;
        for (int i = 0; i < k; i++) {
            first = first.next;
        }

        ListNode second = head;
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        first.next = head;
        head = second.next;
        second.next = null;
        return head;
    }
}
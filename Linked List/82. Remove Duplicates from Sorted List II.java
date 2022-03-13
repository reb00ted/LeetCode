// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode prev = dummy;
        ListNode iter = head;

        while (iter != null) {
            if (iter.next != null && iter.val == iter.next.val) {
                while (iter.next != null && iter.val == iter.next.val) {
                    iter = iter.next;
                }
                iter = iter.next;
            } else {
                prev.next = iter;
                prev = prev.next;
                iter = iter.next;
            }
        }
        prev.next = null;
        return dummy.next;
    }
}
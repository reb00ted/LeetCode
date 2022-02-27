// https://leetcode.com/problems/reorder-list/

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
    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode iter = head;
        while (iter != null) {
            stack.push(iter);
            iter = iter.next;
        }
        int halfSize = stack.size() / 2;

        iter = head;
        for (int i = 0; i < halfSize; i++) {
            ListNode nextNode = iter.next;
            iter.next = stack.pop();
            iter.next.next = nextNode;
            iter = nextNode;
        }
        iter.next = null;
        return;
    }
}


class BetterSolution? {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = reverse(slow.next);
        slow.next = null;
        ListNode first = head;
        ListNode temp;
        
        while (second != null) {
            temp = first.next;
            first.next = second;
            second = second.next;
            first.next.next = temp;
            first = temp;
        }

        return;
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode iter = head;
        ListNode next;

        while (iter != null) {
            next = iter.next;
            iter.next = dummy.next;
            dummy.next = iter;
            iter = next;
        }
        return dummy.next;
    }
}
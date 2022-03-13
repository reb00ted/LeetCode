// https://leetcode.com/problems/copy-list-with-random-pointer/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> matchTable = new HashMap<>();
        Node iter = head;
        Node copy = new Node(iter.val);
        matchTable.put(iter, copy);

        while (iter != null) {
            if (matchTable.containsKey(iter.next)) {
                copy.next = matchTable.get(iter.next);
            } else {
                if (iter.next != null) {
                    copy.next = new Node(iter.next.val);
                    matchTable.put(iter.next, copy.next);
                }
            }

            if (matchTable.containsKey(iter.random)) {
                copy.random = matchTable.get(iter.random);
            } else {
                if (iter.random != null) {
                    copy.random = new Node(iter.random.val);
                    matchTable.put(iter.random, copy.random);
                }
            }

            iter = iter.next;
            copy = copy.next;
        }
        return matchTable.get(head);
    }
}
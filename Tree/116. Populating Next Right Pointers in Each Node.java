// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        connectHelper(root);
        return root;
    }

    public void connectHelper(Node root) {
        if (root == null || root.left == null) return;

        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connectHelper(root.left);
        connectHelper(root.right);
    }
}
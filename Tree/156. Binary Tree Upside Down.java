// https://leetcode.com/problems/binary-tree-upside-down/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        return root.left == null ? root : helper(root.left, root);
    }

    private TreeNode helper(TreeNode root, TreeNode parent) {
        if (root.left == null) {
            root.left = parent.right;
            root.right = parent;
            parent.right = null;
            parent.left = null;
            return root;
        }

        TreeNode newRoot = helper(root.left, root);
        root.left = parent.right;
        root.right = parent;
        parent.right = null;
        parent.left = null;
        return newRoot;
    }
}


// https://leetcode.com/problems/binary-tree-upside-down/discuss/49406/Java-recursive-(O(logn)-space)-and-iterative-solutions-(O(1)-space)-with-explanation-and-figure
class Solution2 {
    // recursive
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;   // node 2 left children
        root.left.right = root;         // node 2 right children
        root.left = null;
        root.right = null;
        return newRoot;
    }

    // iterative
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode prev = null;

        while(curr != null) {
            next = curr.left;

            // swapping nodes now, need temp to keep the previous right child
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}

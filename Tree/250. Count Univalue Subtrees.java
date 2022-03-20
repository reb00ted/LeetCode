// https://leetcode.com/problems/count-univalue-subtrees/

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
    int result = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, root.val);
        return this.result;
    }

    private boolean helper(TreeNode root, int value) {
        if (root == null) {
            return true;
        }

        boolean left = helper(root.left, root.val);
        boolean right = helper(root.right, root.val);
        if (left && right) {
            result++;
        }
        return left && right && root.val == value;
    }
}
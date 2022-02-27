// https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/

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

    public int maxAncestorDiff(TreeNode root) {
        maxAncestorDiffHelper(root, root.val, root.val);
        return result;
    }

    private void maxAncestorDiffHelper(TreeNode root, int min, int max) {
        if (root == null) return;

        result = Math.max(result, Math.abs(min - root.val));
        result = Math.max(result, Math.abs(max - root.val));

        min = Math.min(root.val, min);
        max = Math.max(root.val, max);

        maxAncestorDiffHelper(root.left, min, max);
        maxAncestorDiffHelper(root.right, min, max);
    }
}
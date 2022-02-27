// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

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

    public int sumRootToLeaf(TreeNode root) {
        sumRootToLeafHelper(root, 0);
        return result;
    }

    private void sumRootToLeafHelper(TreeNode root, int value) {
        if (root == null) return;

        value = value * 2 + root.val;
        if (root.left == null && root.right == null) {
            result += value;
            return;
        }

        sumRootToLeafHelper(root.left, value);
        sumRootToLeafHelper(root.right, value);
    }
}
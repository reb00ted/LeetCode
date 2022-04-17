// https://leetcode.com/problems/increasing-order-search-tree/

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
    public TreeNode increasingBST(TreeNode root) {
        TreeNode result = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode iter = root;
        TreeNode prev = null;

        while (true) { // while (!stack.isEmpty() || iter != null)
            while (iter != null) {
                stack.push(iter);
                iter = iter.left;
            }
            if (stack.isEmpty()) break; // while 조건문을 변경하면 스택에는 항상 하나의 노드가 존재하게 되므로 체크 안해도 됨
            iter = stack.pop();
            iter.left = null;

            if (result == null) {
                result = iter;
                prev = iter;
            } else {
                prev.right = iter;
                prev = iter;
            }

            iter = iter.right;
        }
        return result;
    }
}
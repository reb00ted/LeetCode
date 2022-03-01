// https://leetcode.com/problems/maximum-width-of-binary-tree/

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
    class Pair {
        int position;
        TreeNode node;

        Pair(int position, TreeNode node) {
            this.position = position;
            this.node = node;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        int result = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1, root));

        while (!q.isEmpty()) {
            Pair first = q.poll();
            int size = q.size();
            int bias = first.position - 1;
            first.position -= bias;
            if (first.node.left != null) {
                q.add(new Pair(first.position * 2, first.node.left));
            }
            if (first.node.right != null) {
                q.add(new Pair(first.position * 2 + 1, first.node.right));
            }

            for (int i = size; i > 0; i--) {
                Pair curr = q.poll();
                curr.position -= bias;
                result = Math.max(result, curr.position);

                if (curr.node.left != null) {
                    q.add(new Pair(curr.position * 2, curr.node.left));
                }
                if (curr.node.right != null) {
                    q.add(new Pair(curr.position * 2 + 1, curr.node.right));
                }
            }
        }
        return result;
    }
}
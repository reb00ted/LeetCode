// https://leetcode.com/problems/nested-list-weight-sum-ii/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    int maxDepth = 0;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        maxDepth = getMaxDepth(nestedList);
        return calculate(nestedList, 1);
    }

    private int getMaxDepth(List<NestedInteger> nestedList) {
        int maxDepth = 0;
        for (NestedInteger curr : nestedList) {
            if (curr.isInteger()) continue;
            maxDepth = Math.max(maxDepth, getMaxDepth(curr.getList()));
        }

        return maxDepth + 1;
    }

    private int calculate(List<NestedInteger> nestedList, int depth) {
        int weights = 0;
        for (NestedInteger curr : nestedList) {
            if (curr.isInteger()) {
                weights += curr.getInteger() * (maxDepth - depth + 1);
            } else {
                weights += calculate(curr.getList(), depth + 1);
            }
        }

        return weights;
    }
}
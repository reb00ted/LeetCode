// https://leetcode.com/problems/counting-bits/

class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = result[i ^ (i & -i)] + 1;
        }
        return result;
    }
}
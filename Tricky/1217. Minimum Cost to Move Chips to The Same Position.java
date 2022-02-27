// https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/

class Solution {
    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int i : position) {
            if (i % 2 == 1) {
                odd++;
            } else {
                even++;
            }
        }
        return Math.min(odd, even);
    }
}
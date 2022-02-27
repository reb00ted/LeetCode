// https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/

class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int result = 0;

        int i = cost.length - 1;
        while (i >= 2) {
            result += cost[i];
            result += cost[i - 1];
            i -= 3;
        }

        while (i >= 0) {
            result += cost[i];
            i--;
        }
        return result;
    }
}
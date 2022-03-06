// https://leetcode.com/problems/delete-and-earn/

class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] pick = new int[10001];
        for (int x : nums) {
            pick[x] += x;
        }

        int[] dp = new int[10001];
        dp[1] = pick[1];
        dp[2] = Math.max(pick[1], pick[2]);
        for (int i = 3; i <= 10000; i++) {
            dp[i] = Math.max(dp[i - 2] + pick[i], dp[i - 1]);
        }
        return dp[10000];
    }
}
// https://leetcode.com/problems/stone-game-iv/

class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[0] = false;
        dp[1] = true;

        for (int i = 2; i <= n; i++) {
            boolean curr = false;
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
// https://leetcode.com/problems/find-the-town-judge/

class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] receive = new int[n + 1];
        int[] give = new int[n + 1];

        for (int[] t : trust) {
            give[t[0]]++;
            receive[t[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (receive[i] == n - 1 && give[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
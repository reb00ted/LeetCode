// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] mods = new int[60];
        for (int t : time) {
            mods[t % 60]++;
        }

        int result = mods[0] * (mods[0] - 1) / 2;
        result += mods[30] * (mods[30] - 1) / 2;
        for (int i = 1; i < 30; i++) {
            result += mods[i] * mods[60 - i];
        }
        return result;
    }
}
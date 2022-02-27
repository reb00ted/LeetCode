// https://leetcode.com/problems/koko-eating-bananas/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1_000_000_000;

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (isPossible(mid, h, piles)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean isPossible(int k, int h, int[] piles) {
        int hour = 0;
        for (int i = 0; i < piles.length; i++) {
            hour += (piles[i] + k - 1) / k;
        }
        return hour <= h;
    }
}
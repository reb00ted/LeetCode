// https://leetcode.com/problems/maximum-running-time-of-n-computers/

class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long left = 1, right = (long) 1e14 + 1;
        while (left < right) {
            long mid = (left + right) >>> 1;
            if (isPossible(batteries, n, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right - 1;
    }

    private boolean isPossible(int[] batteries, int n, long hour) {
        long total = 0;
        for (int battery : batteries) {
            total += Math.min(battery, hour);
        }
        return total / n >= hour;
    }
}
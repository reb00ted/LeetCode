// https://leetcode.com/problems/remove-covered-intervals/

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int interval = 0;
        int end = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (end < intervals[i][1]) {
                interval++;
                end = intervals[i][1];
            }
        }
        return interval;
    }
}
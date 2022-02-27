// https://leetcode.com/problems/merge-intervals/

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {
            int[] curr = new int[]{ intervals[i][0], intervals[i][1] };
            i++;

            while (i < intervals.length && curr[1] >= intervals[i][0]) {
                curr[1] = Math.max(curr[1], intervals[i][1]);
                i++;
            }
            result.add(curr);
        }

        return result.toArray(int[][]::new);
    }
}
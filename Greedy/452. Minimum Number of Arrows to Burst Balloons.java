// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            return a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]);
        });

        int shot = 0;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (end < points[i][0]) {
                shot++;
                end = points[i][1];
            } else {
                end = Math.min(end, points[i][1]);
            }
        }
        shot++;
        return shot;
    }
}
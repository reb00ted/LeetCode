// https://leetcode.com/problems/spiral-matrix-ii/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        int step = 0;
        while (num < n * n) {
            for (int col = step; col < n - step - 1; col++) {
                result[step][col] = num++;
            }
            for (int row = step; row < n - step - 1; row++) {
                result[row][n - step - 1] = num++;
            }
            for (int col = n - step - 1; col > step; col--) {
                result[n - step - 1][col] = num++;
            }
            for (int row = n - step - 1; row > step; row--) {
                result[row][step] = num++;
            }
            step++;
        }
        if (n % 2 == 1) {
            result[step][step] = num;
        }
        return result;
    }
}
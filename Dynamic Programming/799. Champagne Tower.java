// https://leetcode.com/problems/champagne-tower/

class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] table = new double[query_row + 1][query_row + 1];
        table[0][0] = poured;
        for (int row = 0; row < query_row; row++) {
            for (int col = 0; col <= row; col++) {
                double overflow = Math.max(0, (table[row][col] - 1) / 2);
                table[row + 1][col] += overflow;
                table[row + 1][col + 1] += overflow;
            }
        }
        return Math.min(table[query_row][query_glass], 1);
    }
}
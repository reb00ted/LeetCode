// https://leetcode.com/problems/search-a-2d-matrix/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int r = mid / col, c = mid % col;
            if (matrix[r][c] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int r = left / col, c = left % col;
        return matrix[r][c] == target;
    }
}
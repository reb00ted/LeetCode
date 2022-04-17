// https://leetcode.com/problems/shift-2d-grid/

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int pos = i * col + j + k;
                int r = (pos / col) % row;
                int c = pos % col;
                result[r][c] = grid[i][j];
            }
        }
        return Arrays.stream(result)
                .map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
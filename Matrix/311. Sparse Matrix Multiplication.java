// https://leetcode.com/problems/sparse-matrix-multiplication/

class Solution {
    class Pair {
        int value;
        int col;

        Pair(int value, int col) {
            this.value = value;
            this.col = col;
        }
    }
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int row = mat1.length, col = mat2[0].length;
        int k = mat1[0].length;
        int[][] result = new int[row][col];

        Queue<Pair>[] rows = new Queue[row];
        for (int r = 0; r < row; r++) {
            rows[r] = new LinkedList<Pair>();
            for (int c = 0; c < mat1[r].length; c++) {
                if (mat1[r][c] != 0) {
                    rows[r].add(new Pair(mat1[r][c], c));
                }
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                for (Pair pair : rows[r]) {
                    result[r][c] += pair.value * mat2[pair.col][c];
                }
            }
        }
        return result;
    }
}
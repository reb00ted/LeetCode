// https://leetcode.com/problems/maximal-square/

class Solution {
    public int maximalSquare(char[][] matrix) {
        int maxSize = 0;
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];

        for (int i = 0; i < col; i++) {
            dp[0][i] = (int) (matrix[0][i] - '0');
            maxSize += (int) (matrix[0][i] - '0');
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = (int) (matrix[i][0] - '0');
            maxSize += (int) (matrix[i][0] - '0');
        }

        maxSize = Math.min(maxSize, 1);
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '0') continue;

                dp[i][j] = IntStream.of(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]).min().getAsInt() + 1;
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }

        return maxSize * maxSize;
    }
}
// https://leetcode.com/problems/path-with-maximum-minimum-value/

class Solution {
    class Pair {
        int row, col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    int row, col;
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};

    public int maximumMinimumPath(int[][] grid) {
        row = grid.length;
        col = grid[0].length;

        int left = 0, right = (int) 1e9 + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (isPossible(grid, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    private boolean isPossible(int[][] grid, int min) {
        boolean[][] visited = new boolean[row][col];
        if (grid[0][0] < min) {
            return false;
        }
        visited[0][0] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int r = curr.row, c = curr.col;
            if (r == row - 1 && c == col - 1) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nextR = r + dr[i];
                int nextC = c + dc[i];
                if (0 <= nextR && nextR < row && 0 <= nextC && nextC < col) {
                    if (!visited[nextR][nextC] && grid[nextR][nextC] >= min) {
                        visited[nextR][nextC] = true;
                        q.add(new Pair(nextR, nextC));
                    }
                }
            }
        }
        return false;
    }
}
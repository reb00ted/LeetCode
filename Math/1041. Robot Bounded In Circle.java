// https://leetcode.com/problems/robot-bounded-in-circle/

class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        int direction = 0;

        int row = 0, col = 0;
        for (int i = 0; i < 4; i++) {
            for (char ch : instructions.toCharArray()) {
                if (ch == 'G') {
                    row += dr[direction];
                    col += dc[direction];
                } else if (ch == 'R') {
                    direction = (direction + 1) % 4;
                } else { // 'L'
                    direction = (direction - 1 + 4) % 4;
                }
            }

            if (row == 0 && col == 0) return true;
        }
        return false;
    }
}
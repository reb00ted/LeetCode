// https://leetcode.com/problems/the-maze-iii/

class Solution {
    class Data {
        int row;
        int col;
        int move;
        String direction;

        Data(int row, int col, int move, String dir) {
            this.row = row;
            this.col = col;
            this.move = move;
            this.direction = dir;
        }
    }

    int row, col;
    int[][] maze;
    int[] hole;
    int[][] moveCount;

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.maze = maze;
        this.hole = hole;
        this.row = maze.length;
        this.col = maze[0].length;

        this.moveCount = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(moveCount[i], Integer.MAX_VALUE);
        }
        this.moveCount[ball[0]][ball[1]] = 0;

        int minMove = Integer.MAX_VALUE;
        String result = "Z";
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> a.move - b.move);
        pq.add(new Data(ball[0], ball[1], 0, ""));

        while (!pq.isEmpty()) {
            Data curr = pq.poll();
            if (curr.move > minMove) {
                break;
            }
            if (curr.move > this.moveCount[curr.row][curr.col]) {
                continue;
            }
            if (curr.row == hole[0] && curr.col == hole[1]) {
                if (curr.move < minMove) {
                    minMove = curr.move;
                    result = curr.direction;
                } else if (curr.move == minMove && result.compareTo(curr.direction) > 0) {
                    result = curr.direction;
                }
                continue;
            }

            Data next;
            next = down(curr);
            if (next != null) {
                pq.add(next);
            }

            next = left(curr);
            if (next != null) {
                pq.add(next);
            }

            next = right(curr);
            if (next != null) {
                pq.add(next);
            }

            next = up(curr);
            if (next != null) {
                pq.add(next);
            }
        }
        return minMove == Integer.MAX_VALUE ? "impossible" : result;
    }

    private Data left(Data curr) {
        int c = curr.col - 1;
        int move = 0;
        while (c >= 0) {
            if (this.maze[curr.row][c] == 1) {
                break;
            }
            move++;
            if (curr.row == hole[0] && c == hole[1]) {
                return new Data(curr.row, c, curr.move + move, curr.direction + "l");
            }
            c--;
        }
        c++;

        if (move > 0 && curr.move + move <= this.moveCount[curr.row][c]) {
            this.moveCount[curr.row][c] = curr.move + move;
            return new Data(curr.row, c, curr.move + move, curr.direction + "l");
        }
        return null;
    }

    private Data right(Data curr) {
        int c = curr.col + 1;
        int move = 0;
        while (c < this.col) {
            if (this.maze[curr.row][c] == 1) {
                break;
            }
            move++;
            if (curr.row == hole[0] && c == hole[1]) {
                return new Data(curr.row, c, curr.move + move, curr.direction + "r");
            }
            c++;
        }
        c--;

        if (move > 0 && curr.move + move <= moveCount[curr.row][c]) {
            this.moveCount[curr.row][c] = curr.move + move;
            return new Data(curr.row, c, curr.move + move, curr.direction + "r");
        }
        return null;
    }

    private Data up(Data curr) {
        int r = curr.row - 1;
        int move = 0;
        while (r >= 0) {
            if (this.maze[r][curr.col] == 1) {
                break;
            }
            move++;
            if (r == hole[0] && curr.col == hole[1]) {
                return new Data(r, curr.col, curr.move + move, curr.direction + "u");
            }
            r--;
        }
        r++;

        if (move > 0 && curr.move + move <= moveCount[r][curr.col]) {
            this.moveCount[r][curr.col] = curr.move + move;
            return new Data(r, curr.col, curr.move + move, curr.direction + "u");
        }
        return null;
    }

    private Data down(Data curr) {
        int r = curr.row + 1;
        int move = 0;
        while (r < this.row) {
            if (this.maze[r][curr.col] == 1) {
                break;
            }
            move++;
            if (r == hole[0] && curr.col == hole[1]) {
                return new Data(r, curr.col, curr.move + move, curr.direction + "d");
            }
            r++;
        }
        r--;

        if (move > 0 && curr.move + move <= moveCount[r][curr.col]) {
            this.moveCount[r][curr.col] = curr.move + move;
            return new Data(r, curr.col, curr.move + move, curr.direction + "d");
        }
        return null;
    }
}
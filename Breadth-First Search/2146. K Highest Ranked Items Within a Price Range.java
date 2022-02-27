// https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/

class Solution {
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        List<List<Integer>> candidate = new ArrayList<>();
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        visited[start[0]][start[1]] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        int dist = 0;
        while (!q.isEmpty() && candidate.size() < k) {
            for (int i = q.size(); i > 0; i--) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1];
                if (pricing[0] <= grid[r][c] && grid[r][c] <= pricing[1]) {
                    candidate.add(List.of(r, c, dist));
                }

                for (int j = 0; j < 4; j++) {
                    int nextR = r + dr[j], nextC = c + dc[j];
                    if (0 <= nextR && nextR < row && 0 <= nextC && nextC < col && grid[nextR][nextC] != 0 && !visited[nextR][nextC]) {
                        q.add(new int[]{nextR, nextC});
                        visited[nextR][nextC] = true;
                    }
                }
            }
            dist++;

        }

        return candidate.stream()
                .sorted((a, b) -> {
                    int r1 = a.get(0), c1 = a.get(1);
                    int r2 = b.get(0), c2 = b.get(1);
                    int dist1 = a.get(2);
                    int dist2 = b.get(2);
                    if (dist1 != dist2) return dist1 - dist2;
                    if (grid[r1][c1] != grid[r2][c2]) return grid[r1][c1] - grid[r2][c2];
                    if (r1 != r2) return r1 - r2;
                    return c1 - c2;
                })
                .limit(k)
                .map(list -> list.subList(0, 2))
                .collect(Collectors.toList());
    }
}
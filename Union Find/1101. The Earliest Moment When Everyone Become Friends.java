// https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/

class Solution {
    class UnionFind {
        int n;
        int[] group;
        int[] size;

        UnionFind(int n) {
            this.n = n;
            group = IntStream.range(0, n).toArray();
            size = IntStream.range(0, n).map(i -> 1).toArray();
        }

        int getRoot(int x) {
            if (group[x] == x) return x;
            return group[x] = getRoot(group[x]);
        }

        int union(int x, int y) {
            int rootX = getRoot(x);
            int rootY = getRoot(y);

            if (size[rootX] < size[rootY]) {
                size[rootY] += size[rootX];
                group[rootX] = rootY;
                return size[rootY];
            } else {
                size[rootX] += size[rootY];
                group[rootY] = rootX;
                return size[rootX];
            }
        }
    }

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);

        UnionFind uf = new UnionFind(n);
        for (int[] log : logs) {
            int x = uf.getRoot(log[1]);
            int y = uf.getRoot(log[2]);

            if (x == y) continue;
            if (uf.union(x, y) == n) return log[0];
        }
        return -1;
    }
}
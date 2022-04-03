// https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/

class Solution {
    class Pair {
        int row, soldiers;

        Pair(int row, int soldiers) {
            this.row = row;
            this.soldiers = soldiers;
        }
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        Pair[] soldiers = new Pair[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int left = 0, right = mat[i].length;
            int mid;
            while (left < right) {
                mid = (left + right) >> 1;
                if (mat[i][mid] == 1) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            soldiers[i] = new Pair(i, left - 1);
        }

        Arrays.sort(soldiers, (a, b) -> {
            if (a.soldiers == b.soldiers) {
                return a.row - b.row;
            }
            return a.soldiers - b.soldiers;
        });
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = soldiers[i].row;
        }
        return result;
    }
}
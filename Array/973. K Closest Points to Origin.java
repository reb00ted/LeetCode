// https://leetcode.com/problems/k-closest-points-to-origin/

// 정렬을 이용한 풀이
// 이 문제는 Quickselect 알고리즘을 이용해서 해결할 수도 있다.
// Quickselect 알고리즘은 평균적인 경우 O(n), 최악의 경우 O(n ^ 2) 시간 복잡도를 가진다.
class Solution {
    class Pair {
        int[] point;
        int dist;

        Pair(int[] point) {
            this.point = point;
            this.dist = point[0] * point[0] + point[1] * point[1];
        }

        int getDist() {
            return this.dist;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        return Arrays.stream(points)
                .map(Pair::new)
                .sorted(Comparator.comparing(Pair::getDist))
                .limit(k)
                .map(pair -> pair.point)
                .toArray(int[][]::new);
    }
}
// https://leetcode.com/problems/two-city-scheduling/

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        // 두 도시 비용 차이를 내림차순으로 정렬
        Arrays.sort(costs, (a, b) -> Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]));
        int n = costs.length / 2;
        int a = 0, b = 0;

        int totalCost = 0;
        for (int i = 0; i < costs.length; i++) {
            if (a == n) { // A 도시 마감 -> B 도시
                totalCost += costs[i][1];
            } else if (b == n) { // B 도시 마감 -> A 도시
                totalCost += costs[i][0];
            } else {
                // i + 1 에서 비용 차이는 i 에서의 비용 차이보다 작거나 같음
                if (costs[i][0] < costs[i][1]) {
                    a++;
                    totalCost += costs[i][0];
                } else {
                    b++;
                    totalCost += costs[i][1];
                }
            }
        }
        return totalCost;
    }
}
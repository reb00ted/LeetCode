// https://leetcode.com/problems/shortest-path-visiting-all-nodes/

// 외판원 알고리즘 응용
class Solution {
    int n;
    int[][] dp;
    int[][] dist;

    public int shortestPathLength(int[][] graph) {
        n = graph.length;
        dp = new int[n][1 << n];
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 987654321);
            for (int j : graph[i]) {
                dist[i][j] = dist[j][i] = 1;
            }
            dist[i][i] = 0;
        }

        // 플로이드 워셜 알고리즘을 이용하여 각 노드간의 거리 계산
        for (int mid = 0; mid < n; mid++) {
            for (int src = 0; src < n; src++) {
                for (int des = 0; des < n; des++) {
                    if (dist[src][des] > dist[src][mid] + dist[mid][des]) {
                        dist[src][des] = dist[src][mid] + dist[mid][des];
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        int remain = (1 << n) - 1;
        for (int i = 0; i < n; i++) {
            result = Math.min(result, search(i, 0, remain ^ (1 << i)));
        }
        return result;
    }

    private int search(int currNode, int currCost, int remain) {
        if (remain == 0) {
            return currCost;
        }
        if (dp[currNode][remain] > 0) {
            return currCost + dp[currNode][remain];
        }

        int traverseCost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((remain & (1 << i)) > 0) {
                traverseCost = Math.min(traverseCost, search(i, dist[currNode][i], remain ^ (1 << i)));
            }
        }
        dp[currNode][remain] = traverseCost;
        return currCost + traverseCost;
    }
}
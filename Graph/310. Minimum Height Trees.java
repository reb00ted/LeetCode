// https://leetcode.com/problems/minimum-height-trees/

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Set<Integer>[] graph = new Set[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 1) {
                leaves.add(i);
            }
        }

        int processed = 0;
        while (!leaves.isEmpty()) {
            if (processed + leaves.size() == n) {
                return leaves.stream().collect(Collectors.toList());
            }

            for (int i = leaves.size(); i > 0; i--) {
                int leafNode = leaves.poll();
                graph[leafNode].forEach(neighbor -> {
                    graph[neighbor].remove(leafNode);
                    if (graph[neighbor].size() == 1) {
                        leaves.add(neighbor);
                    }
                });
                processed++;
            }
        }
        return List.of(0);
    }
}
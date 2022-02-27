// https://leetcode.com/problems/find-all-people-with-secret/

class Solution {
    boolean[] hasSecret;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        this.hasSecret = new boolean[n];
        this.hasSecret[0] = true;
        this.hasSecret[firstPerson] = true;

        // 같은 시간에 만나는 미팅들을 묶기 위해 미팅 시간을 기준으로 정렬
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        int i = 0;
        while (i < meetings.length) {
            int time = meetings[i][2];
            int j = i;
            List<int[]> sameTimeMeetings = new ArrayList<>();
            // 같은 시간에 만나는 미팅들 모음
            while (j < meetings.length && time == meetings[j][2]) {
                sameTimeMeetings.add(meetings[j]);
                j++;
            }

            // 그래프로 변환
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int[] meet : sameTimeMeetings) {
                if (!graph.containsKey(meet[0])) {
                    graph.put(meet[0], new ArrayList<>());
                }
                if (!graph.containsKey(meet[1])) {
                    graph.put(meet[1], new ArrayList<>());
                }
                graph.get(meet[0]).add(meet[1]);
                graph.get(meet[1]).add(meet[0]);
            }

            // dfs 수행
            Map<Integer, Integer> groupTable = new HashMap<>();
            Set<Integer> hasSecretGroups = new HashSet<>();
            int groupNo = 1;
            for (int node : graph.keySet()) {
                if (!groupTable.containsKey(node)) {
                    dfs(node, groupNo, graph, groupTable, hasSecretGroups);
                    groupNo++;
                }
            }

            for (int node : graph.keySet()) {
                this.hasSecret[node] |= hasSecretGroups.contains(groupTable.get(node));
            }

            i = j;
        }

        return IntStream.range(0, n)
                .filter(i -> hasSecret[i])
                .boxed()
                .collect(Collectors.toList());
    }

    private void dfs(int node, int groupNo, Map<Integer, List<Integer>> graph,
                     Map<Integer, Integer> groupTable, Set<Integer> hasSecretGroups) {
        groupTable.put(node, groupNo);
        if (this.hasSecret[node]) {
            hasSecretGroups.add(groupNo);
        }

        for (int next : graph.get(node)) {
            if (!groupTable.containsKey(next)) {
                dfs(next, groupNo, graph, groupTable, hasSecretGroups);
            }
        }
        return;
    }
}


// Graph 대신 Union-Find 적용
class BtterSolution {

}
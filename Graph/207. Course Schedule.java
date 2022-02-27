// https://leetcode.com/problems/course-schedule/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
        }

        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, status)) return false;
        }
        return true;
    }

    // status[i] = 0, 미방문 상태
    // status[i] = 1, 현재 dfs 수행 중인 상태
    // status[i] = 2, dfs 완료 상태 -> 해당 노드를 포함하는 사이클은 존재하지 않음
    // 사이클이 존재하면 false 반환
    private boolean dfs(int curr, List<Integer>[] graph, int[] status) {
        if (status[curr] == 1) return false;
        if (status[curr] == 2) return true;

        status[curr] = 1;
        for (int next : graph[curr]) {
            if (!dfs(next, graph, status)) return false;
        }

        status[curr] = 2;
        return true;
    }
}
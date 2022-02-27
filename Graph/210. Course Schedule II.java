// https://leetcode.com/problems/course-schedule-ii/

class Solution {
    int n;
    int[] visitStatus;
    ArrayList<Integer> finishOrder;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
        }
        this.n = numCourses;
        this.visitStatus = new int[numCourses];
        this.finishOrder = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph)) return new int[0];
        }

        return IntStream.range(0, numCourses)
                .map(i -> this.finishOrder.get(numCourses - i - 1))
                .toArray();
    }

    // dfs 를 이용한 위상정렬
    // 사이클이 존재해서 위상정렬이 불가능한 경우 false 반환
    private boolean dfs(int curr, List<Integer>[] graph) {
        if (this.visitStatus[curr] == 1) return false;
        if (this.visitStatus[curr] == 2) return true;

        this.visitStatus[curr] = 1;
        for (int next : graph[curr]) {
            if (!dfs(next, graph)) return false;
        }

        this.visitStatus[curr] = 2;
        this.finishOrder.add(curr);
        return true;
    }
}
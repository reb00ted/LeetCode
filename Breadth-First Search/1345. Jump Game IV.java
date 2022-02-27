// https://leetcode.com/problems/jump-game-iv/

class Solution {
    public int minJumps(int[] arr) {
        // 숫자가 등장하는 인덱스 그룹화
        Map<Integer, List<Integer>> indexTable = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!indexTable.containsKey(arr[i])) {
                indexTable.put(arr[i], new ArrayList<Integer>());
            }
            indexTable.get(arr[i]).add(i);
        }

        int n = arr.length;
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();

        // 숫자가 같은 인덱스로의 점프는 1번만 수행하기 위해 확인하는 Set
        // -> 너비 우선 탐색이기 때문에 이전에 방문 했던 비용보다 더 적은 비용으로 방문하는 것은 불가능하다.
        Set<Integer> visited = new HashSet<>();

        cost[0] = 0;
        q.add(0);
        while (!q.isEmpty()) {
            int index = q.poll();
            if (index == n - 1) {
                return cost[index];
            }

            // 숫자가 같은 인덱스로 점프
            int num = arr[index];
            if (!visited.contains(num)) {
                for (int i : indexTable.get(num)) {
                    if (cost[i] > cost[index] + 1) {
                        cost[i] = cost[index] + 1;
                        q.add(i);
                    }
                }
                visited.add(num);
            }

            // 인접한 인덱스로 점프
            if(index - 1 > 0 && cost[index - 1] > cost[index] + 1) {
                cost[index - 1] = cost[index] + 1;
                q.add(index - 1);
            }
            if (index + 1 < n && cost[index + 1] > cost[index] + 1) {
                cost[index + 1] = cost[index] + 1;
                q.add(index + 1);
            }
        }
        return -1;
    }
}
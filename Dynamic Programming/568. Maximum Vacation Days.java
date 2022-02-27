// https://leetcode.com/problems/maximum-vacation-days/

class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length;
        int k = days[0].length;

        // table[week][city] : week 주에 city 도시에서 지낼 때 얻을 수 있는 최대 누적 휴가 기간
        int[][] table = new int[k][n];
        boolean[] visit = new boolean[n];
        table[0][0] = days[0][0];
        visit[0] = true;
        for (int i = 1; i < n; i++) {
            if (flights[0][i] > 0) {
                table[0][i] = days[i][0];
                visit[i] = true;
            }
        }

        for (int week = 1; week < k; week++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (visit[from] && (from == to || flights[from][to] > 0)) {
                        table[week][to] = Math.max(table[week - 1][from] + days[to][week], table[week][to]);
                        visit[to] = true;
                    }
                }
            }
        }

        return Arrays.stream(table[k - 1]).max().getAsInt();
    }
}
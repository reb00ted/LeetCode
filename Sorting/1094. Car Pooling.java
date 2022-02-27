// https://leetcode.com/problems/car-pooling/

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        int passengers = 0;
        int i = 0;
        int n = trips.length;
        while (i < n) {
            if (!pq.isEmpty() && pq.peek()[2] <= trips[i][1]) {
                passengers -= pq.poll()[0];
            } else {
                passengers += trips[i][0];
                pq.add(trips[i]);
                i++;
                if (passengers > capacity) return false;
            }
        }
        return true;
    }
}
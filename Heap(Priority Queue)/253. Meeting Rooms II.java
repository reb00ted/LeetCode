// https://leetcode.com/problems/meeting-rooms-ii/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int rooms = 0;
        for (int i = 0; i < intervals.length; i++) {
            while (!pq.isEmpty() && pq.peek() <= intervals[i][0]) {
                pq.poll();
            }
            if (pq.size() == rooms) {
                rooms++;
            }
            pq.add(intervals[i][1]);
        }
        return rooms;
    }
}
// https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        List<Integer> result = new ArrayList<>();
        List<Integer> table = new ArrayList<>();
        for (int x : obstacles) {
            if (table.isEmpty() || table.get(table.size() - 1) <= x) {
                table.add(x);
                result.add(table.size());
            } else {
                int index = findCeiling(table, x);
                table.set(index, x);
                result.add(index + 1);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private int findCeiling(List<Integer> table, int value) {
        int left = 0, right = table.size() - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (value < table.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
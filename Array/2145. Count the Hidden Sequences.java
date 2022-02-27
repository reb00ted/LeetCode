// https://leetcode.com/problems/count-the-hidden-sequences/

class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int min = 0, max = 0;
        int accumulate = 0;
        for (int diff : differences) {
            accumulate += diff;
            min = Math.min(min, accumulate);
            max = Math.max(max, accumulate);
        }

        int range = Math.abs(max - min);
        if (lower + range > upper) {
            return 0;
        }
        return upper - (lower + range - 1);
    }
}
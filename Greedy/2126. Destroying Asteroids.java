// https://leetcode.com/problems/destroying-asteroids/

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long accumulate = mass;
        for (int asteroid : asteroids) {
            if (accumulate < asteroid) return false;
            accumulate += asteroid;
        }
        return true;
    }
}
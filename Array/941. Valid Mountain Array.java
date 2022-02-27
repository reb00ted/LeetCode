// https://leetcode.com/problems/valid-mountain-array/

class Solution {
    public boolean validMountainArray(int[] arr) {
        int i = 0;
        while (i + 1 < arr.length && arr[i] < arr[i + 1]) {
            i++;
        }
        if (i == 0 || i + 1 == arr.length) return false;

        while (i + 1 < arr.length && arr[i] > arr[i + 1]) {
            i++;
        }
        return i + 1 == arr.length;
    }
}
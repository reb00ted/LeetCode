// https://leetcode.com/problems/fixed-point/

class Solution {
    public int fixedPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid <= arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[right] == right ? right : -1;
    }
}
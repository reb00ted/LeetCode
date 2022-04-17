// https://leetcode.com/problems/minimize-product-sum-of-two-arrays/

class Solution {
    public int minProductSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int result = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            result += nums1[i] * nums2[n - i - 1];
        }
        return result;
    }
}
// https://leetcode.com/problems/arithmetic-slices/

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int result = 0;
        int stack = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                stack++;
                result += stack;
            } else {
                stack = 0;
            }
        }
        return result;
    }
}
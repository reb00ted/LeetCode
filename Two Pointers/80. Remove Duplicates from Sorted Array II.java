// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

class Solution {
    public int removeDuplicates(int[] nums) {
        int left = 0, right = 0;
        int count = 0;
        while (right < nums.length) {
            if (right == 0 || nums[right] != nums[right - 1]) {
                nums[left] = nums[right];
                left++;
                count = 1;
            } else {
                count++;
                if (count <= 2) {
                    nums[left] = nums[right];
                    left++;
                }
            }
            right++;
        }
        return left;
    }
}

class Solution2 {
    public int removeDuplicates(int[] nums) {
        int index = 1;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count <= 2) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index + 1;
    }
}
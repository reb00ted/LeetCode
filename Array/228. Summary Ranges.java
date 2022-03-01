// https://leetcode.com/problems/summary-ranges/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        int n = nums.length;
        int startIndex = -1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                if (startIndex == -1) {
                    startIndex = i - 1;
                }
            } else {
                if (startIndex == -1) {
                    result.add(String.valueOf(nums[i - 1]));
                } else {
                    result.add(String.format("%d->%d", nums[startIndex], nums[i - 1]));
                    startIndex = -1;
                }
            }
        }

        if (startIndex == -1) {
            result.add(String.valueOf(nums[n - 1]));
        } else {
            result.add(String.format("%d->%d", nums[startIndex], nums[n - 1]));
        }
        return result;
    }
}
// https://leetcode.com/problems/subsets/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subset(nums, 0, new ArrayDeque<>(), result);
        return result;
    }

    private void subset(int[] nums, int i, ArrayDeque<Integer> stack, List<List<Integer>> result) {
        if (i == nums.length) {
            result.add(new ArrayList<>(stack));
            return;
        }

        stack.push(nums[i]);
        subset(nums, i + 1, stack, result);
        stack.pop();
        subset(nums, i + 1, stack, result);
    }
}


class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int upper = 1 << n;
        for (int i = 0; i < upper; i++) {
            List<Integer> curr = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    curr.add(nums[j]);
                }
            }
            result.add(curr);
        }
        return result;
    }
}
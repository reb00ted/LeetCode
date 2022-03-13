// https://leetcode.com/problems/combination-sum/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combination(0, target, 0, new Stack<>(), candidates, result);
        return result;
    }

    private void combination(int sum, int target, int i, Stack<Integer> elements, int[] candidates, List<List<Integer>> result) {
        if (sum == target) {
            result.add(new ArrayList<>(elements));
            return;
        }

        for (; i < candidates.length; i++) {
            int x = candidates[i];
            if (sum + x > target) {
                continue;
            }

            elements.push(x);
            combination(sum + x, target, i, elements, candidates, result);
            elements.pop();
        }
    }
}
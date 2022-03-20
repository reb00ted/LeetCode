// https://leetcode.com/problems/sequence-reconstruction/

class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        int n = nums.length;
        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
        }

        for (List<Integer> seq : sequences) {
            int prev = seq.get(0);
            int curr;
            for (int i = 1; i < seq.size(); i++) {
                curr = seq.get(i);
                graph[prev].add(curr);
                prev = curr;
            }
        }

        int prev = nums[0], curr;
        for (int i = 1; i < nums.length; i++) {
            curr = nums[i];
            if (!graph[prev].contains(curr)) {
                return false;
            }
            prev = curr;
        }
        return true;
    }
}
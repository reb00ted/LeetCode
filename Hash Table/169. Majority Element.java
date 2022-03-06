// https://leetcode.com/problems/majority-element/

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Long> count = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        return count.entrySet().stream()
                .max((a, b) -> (int) (a.getValue() - b.getValue()))
                .get().getKey();
    }
}
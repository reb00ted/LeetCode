// https://leetcode.com/problems/top-k-frequent-elements/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> count = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return count.entrySet().stream()
                .sorted((a, b) -> b.getValue().intValue() - a.getValue().intValue())
                .limit(k)
                .mapToInt(a -> a.getKey().intValue())
                .toArray();
    }
}
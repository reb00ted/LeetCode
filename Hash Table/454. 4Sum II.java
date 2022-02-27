// https://leetcode.com/problems/4sum-ii/

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Long> group1 = grouping2(nums1, nums2);
        Map<Integer, Long> group2 = grouping2(nums3, nums4);

        int result = 0;
        for (int value : group1.keySet()) {
            result += group1.get(value) * group2.getOrDefault(-value, 0L);
        }
        return result;
    }

    // 1052 ms, 112 MB
    // 스트림을 사용하면서 이렇게 까지 많은 차이가 발생한적은 없었는데
    private Map<Integer, Long> grouping(int[] arr1, int[] arr2) {
        return Arrays.stream(arr1)
                .flatMap(num1 -> Arrays.stream(arr2).map(num2 -> num1 + num2))
                .boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
    }

    // 375 ms, 71.7 MB
    private Map<Integer, Long> grouping2(int[] arr1, int[] arr2) {
        Map<Integer, Long> result = new HashMap<>();
        for (int x : arr1) {
            for (int y : arr2) {
                result.put(x + y, result.getOrDefault(x + y, 0L) + 1);
            }
        }
        return result;
    }
}

// 281 ms, 58.9 MB
class Solution2 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Long> group = grouping(nums1, nums2);

        int result = 0;
        for (int x : nums3) {
            for (int y : nums4) {
                result += group.getOrDefault(-(x + y), 0L);
            }
        }
        return result;
    }

    private Map<Integer, Long> grouping(int[] arr1, int[] arr2) {
        Map<Integer, Long> result = new HashMap<>();
        for (int x : arr1) {
            for (int y : arr2) {
                result.put(x + y, result.getOrDefault(x + y, 0L) + 1);
            }
        }
        return result;
    }
}

// 107 ms, 42 MB
// Solution2 에서 바뀐 것은 Map<Integer, Long> -> Map<Integer, Integer> 뿐인데 속도 차이 무엇??
class Solution3 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> group = grouping(nums1, nums2);

        int result = 0;
        for (int x : nums3) {
            for (int y : nums4) {
                result += group.getOrDefault(-(x + y), 0);
            }
        }
        return result;
    }

    private Map<Integer, Integer> grouping(int[] arr1, int[] arr2) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int x : arr1) {
            for (int y : arr2) {
                result.put(x + y, result.getOrDefault(x + y, 0) + 1);
            }
        }
        return result;
    }
}
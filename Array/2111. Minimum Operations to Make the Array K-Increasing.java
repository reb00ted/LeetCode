// https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing/

class Solution {
    public int kIncreasing(int[] arr, int k) {
        int cost = 0;

        // k 를 이용해서 모듈로 합동 관계인 숫자들을 순차적으로 그룹화한다.
        for (int i = 0; i < k; i++) {
            int index = i;
            List<Integer> group = new ArrayList<>();
            while (index < arr.length) {
                group.add(arr[index]);
                index += k;
            }
            // 각 그룹에서 LIS 에 속하지 않는 숫자들을 변환하는 것이 최소의 변환 횟수를 가진다.
            cost += (group.size() - getLISLength(group));
        }
        return cost;
    }

    // 그룹의 LIS 길이 계산
    private int getLISLength(List<Integer> group) {
        ArrayList<Integer> table = new ArrayList<>();
        int i = -1;
        for (int x : group) {
            i++;
            int left = 0, right = table.size();
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (table.get(mid) <= x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left < table.size()) {
                table.set(left, x);
            } else {
                table.add(x);
            }
        }
        return table.size();
    }
}
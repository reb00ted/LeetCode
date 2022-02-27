// https://leetcode.com/problems/smallest-integer-divisible-by-k/

class Solution {
    public int smallestRepunitDivByK(int k) {
        int n = 1;
        int length = 1;

        Set<Integer> passed = new HashSet<>();
        while (true) {
            while (n < k) {
                n = n * 10 + 1;
                length++;
            }

            if (n % k == 0) break;
            if (passed.contains(n)) return -1;
            passed.add(n);
            n %= k;
        }
        return length;
    }
}

// 비둘기집 원리를 이용하면 Set 없이도 풀이 가능
// k 에 대해서 어떤 수를 k 로 나누었을 때 나올 수 있는 나머지는 0 ~ k - 1 이다.
// -> k 번 나눌때까지 나누어떨어지지 않는 경우는 사이클이 존재하므로 -1 리턴
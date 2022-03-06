// https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/

/*
* n = 1 인 경우, P D 한 가지 경우만 존재
* n = k 인 경우, n = k - 1인 경우의 수로부터 (2 * (k - 1) + 1)개의 빈 공간 중에서 k번째 P와 D를 배치해서 경우의 수를 구할 수 있음
* __ ?1 __ ?2 __ ... __ ?(2k - 3) __ ?(2k - 2) __, [?i 는 임의의 올바른 순서의 P 또는 D, __ 는 P와 D가 들어갈 수 있는 공간]
*
* 이때 P와 D를 붙여서 배치하는 경우와 분리해서 배치하는 경우 2가지로 나뉨
* - P와 D를 연달아 배치하는 경우 -> 2 * (k - 1) + 1 개의 빈 공간 중에서 하나를 선택
* -> combination(2 * (k - 1) + 1, 1)
*
* - P와 D를 분리해서 배치하는 경우 -> 2 * (k - 1) + 1 개의 빈 공간 중에서 두 개를 선택
* -> combination(2 * (k - 1) + 1, 2)
*
* 따라서 dp[k] = dp[k - 1] * (combination(2 * (k - 1) + 1, 1) + combination(2 * (k - 1) + 1, 2))
*             = dp[k - 1] * combination(2 * (k - 1) + 2, 2)
* */

class Solution {
    public int countOrders(int n) {
        long[] dp = new long[n + 1];
        int MOD = (int) 1e9 + 7;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int pos = (i - 1) * 2 + 1;
            dp[i] = (dp[i - 1] * pos * (pos + 1) / 2) % MOD;
        }
        return (int) dp[n];
    }
}
// https://leetcode.com/problems/gas-station/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] delta = new int[n];
        for (int i = 0; i < n; i++) {
            delta[i] = gas[i] - cost[i];
        }

        int prev = 0, curr = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (curr < 0) {
                if (delta[i] >= 0) {
                    index = i;
                    prev += curr;
                    curr = delta[i];
                } else {
                    curr += delta[i];
                }
            } else {
                curr += delta[i];
            }
        }

        return prev + curr >= 0 ? index : -1;
    }
}

// 아이디어는 1차원 배열에서 최대 부분합을 구하는 카데인 알고리즘의 아이디어를 응용했으나 코드가 많이 지저분하다...
// total 과 curr 에 각각 gas[i] - cost[i] 값을 누적하고 curr 은 음수가 되면 초기화해서 다음 인덱스부터 다시 시작하는 방식으로
// 구현하면 코드에서 if 문이 많이 사라져서 깔끔해진다.
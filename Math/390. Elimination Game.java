// https://leetcode.com/problems/elimination-game/

/*
* 아이디어 : 재귀 + 상대적인 위치 계산
* ex) n = 17 인 경우
* 1. 17개 정방향 탐색일 때 살아남는 인덱스 위치는?(1-based index)  -->  8. 6번째
* 2. (17 / 2) = 8개 역방향 탐색일 때 살아남는 인덱스 위치는?       -->  7. 3번째
* 3. (8 / 2) = 4개 정방향 탐색일 때 살아남는 인덱스 위치는?        -->  6. 2번째
* 4. (4 / 2) = 2개 역방향 탐색일 때 살아남는 인덱스 위치는?        -->  5. 1번째
*
* */
class Solution {
    public int lastRemaining(int n) {
        return lastRemaining(n, false);
    }

    private int lastRemaining(int n, boolean reverse) {
        if (n == 1) return 1;
        if (n == 2) {
            return reverse ? 1 : 2;
        }

        if (reverse) {
            if ((n % 2) == 1) {
                return lastRemaining(n / 2, false) * 2;
            } else {
                return lastRemaining(n / 2, false) * 2 - 1;
            }
        } else {
            return lastRemaining(n / 2, true) * 2;
        }
    }
}
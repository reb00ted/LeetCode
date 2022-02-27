// https://leetcode.com/problems/fibonacci-number/

class Solution {
    public int fib(int n) {
        if (n < 2) return n;

        int prev = 0, curr = 1, next = 0;
        for (int i = 2; i <= n; i++) {
            next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
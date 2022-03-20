// https://leetcode.com/problems/validate-stack-sequences/

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }

        return i == popped.length;
    }
}
// https://leetcode.com/problems/valid-parentheses/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> match = Map.of(')', '(', '}', '{', ']', '[');

        for (char ch : s.toCharArray()) {
            if (match.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != match.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
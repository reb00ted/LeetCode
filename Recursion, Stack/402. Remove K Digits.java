// https://leetcode.com/problems/remove-k-digits/

class Solution {
    public String removeKdigits(String num, int k) {
        int i = 0, n = num.length();
        Stack<Character> stack = new Stack<>();
        while (i < n && k > 0) {
            char digit = num.charAt(i);
            if (stack.isEmpty()) {
                if (digit != '0') {
                    stack.push(digit);
                }
            } else {
                while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
                    stack.pop();
                    k--;
                }
                if (!(stack.isEmpty() && digit == '0')) {
                    stack.push(digit);
                }
            }
            i++;
        }

        if (stack.isEmpty()) {
            while (i < n && num.charAt(i) == '0') {
                i++;
            }
        }
        if (stack.size() + (n - i) <= k) return "0";
        return assemble(stack, stack.size() - k) + num.substring(i);
    }

    private String assemble(Stack<Character> stack, int size) {
        return stack.stream()
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
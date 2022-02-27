// https://leetcode.com/problems/decode-string/

class Solution {
    class Pair {
        int repeat;
        StringBuilder builder = new StringBuilder();

        Pair(int repeat) {
            this.repeat = repeat;
        }
    }

    public String decodeString(String s) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(1));

        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                stack.peek().builder.append(ch);
            } else if (Character.isDigit(ch)) {
                num = (num * 10) + (ch - '0');
            } else if (ch == '[') {
                stack.push(new Pair(num));
                num = 0;
            } else { // ch == ']'
                Pair pair = stack.pop();
                String str = pair.builder.toString();
                stack.peek().builder.append(str.repeat(pair.repeat));
            }
        }

        Pair pair = stack.pop();
        return pair.builder.toString();
    }
}
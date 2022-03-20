// https://leetcode.com/problems/remove-duplicate-letters/

class Solution {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();
        Set<Character> letters = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (letters.contains(ch)) {
                continue;
            }

            while (!stack.isEmpty() && ch < stack.peek() && lastIndex.get(stack.peek()) > i) {
                letters.remove(stack.poll());
            }
            stack.push(ch);
            letters.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.poll());
        }
        return sb.reverse().toString();
    }
}
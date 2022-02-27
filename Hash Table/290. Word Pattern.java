// https://leetcode.com/problems/word-pattern/

class Solution {
    public boolean wordPattern(String pattern, String s) {
        char[] key = pattern.toCharArray();
        String[] value = s.split(" ");
        if (key.length != value.length) return false;

        Map<Character, String> table = new HashMap<>();
        Map<String, Character> reverseTable = new HashMap<>();

        for (int i = 0; i < key.length; i++) {
            if (table.containsKey(key[i]) || reverseTable.containsKey(value[i])) {
                if (!table.getOrDefault(key[i], "").equals(value[i]) ||
                    key[i] != reverseTable.getOrDefault(value[i], '\0')) {
                    return false;
                }
            } else {
                table.put(key[i], value[i]);
                reverseTable.put(value[i], key[i]);
            }
        }
        return true;
    }
}
// https://leetcode.com/problems/first-unique-character-in-a-string/

class Solution {
    public int firstUniqChar(String s) {
        int[] index = new int[26];
        final int init = 999_999;
        final int duplicate = 9_999_999;
        Arrays.fill(index, init);

        int n = s.length();
        int base = (int) 'a';
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - base;
            if (index[j] == init) {
                index[j] = i;
            } else {
                index[j] = duplicate;
            }
        }

        return Arrays.stream(index).filter(i -> i < n).min().orElse(-1);
    }
}
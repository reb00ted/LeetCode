// https://leetcode.com/problems/permutation-in-string/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int length = s1.length();
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < length; i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        if (compare(count1, count2)) {
            return true;
        }

        for (int i = length; i < s2.length(); i++) {
            count2[s2.charAt(i - length) - 'a']--;
            count2[s2.charAt(i) - 'a']++;
            if (compare(count1, count2)) {
                return true;
            }
        }
        return false;
    }

    private boolean compare(int[] count1, int[] count2) {
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) return false;
        }
        return true;
    }
}
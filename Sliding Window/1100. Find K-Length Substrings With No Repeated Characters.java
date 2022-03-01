// https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/

class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int length = s.length();
        if (length < k) {
            return 0;
        }

        int[] window = new int[26];
        for (int i = 0; i < k; i++) {
            window[s.charAt(i) - 'a']++;
        }

        int nonRepeatable = 0;
        for (int i = 0; i < 26; i++) {
            if (window[i] <= 1) {
                nonRepeatable++;
            }
        }

        int result = nonRepeatable == 26 ? 1 : 0;
        for (int i = k; i < length; i++) {
            int index = s.charAt(i) - 'a';
            if (window[index] == 1) {
                nonRepeatable--;
            }
            window[index]++;

            index = s.charAt(i - k) - 'a';
            window[index]--;
            if (window[index] == 1) {
                nonRepeatable++;
            }

            if (nonRepeatable == 26) {
                result++;
            }
        }
        return result;
    }
}
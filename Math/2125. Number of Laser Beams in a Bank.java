// https://leetcode.com/problems/number-of-laser-beams-in-a-bank/

class Solution {
    public int numberOfBeams(String[] bank) {
        int result = 0;
        int prev = 0;

        for (int i = 0; i < bank.length; i++) {
            int curr = (int) bank[i].codePoints().filter(ch -> ch == '1').count();
            if (curr > 0) {
                result += prev * curr;
                prev = curr;
            }
        }
        return result;
    }
}
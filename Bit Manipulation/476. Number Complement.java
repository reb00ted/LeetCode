// https://leetcode.com/problems/number-complement/

class Solution {
    public int findComplement(int num) {
        long i = 1;
        while (i < num) {
            i <<= 1;
        }
        i--;
        return (int) (i & ~num);
    }
}
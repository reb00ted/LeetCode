// https://leetcode.com/problems/can-place-flowers/

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, length = flowerbed.length;
        while (i < length && n > 0) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else {
                if ((i == 0 || flowerbed[i - 1] == 0) && (i + 1 >= length || flowerbed[i + 1] == 0)) {
                    n--;
                    i += 2;
                } else {
                    i++;
                }
            }
        }

        return n == 0;
    }
}
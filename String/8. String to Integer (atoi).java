// https://leetcode.com/problems/string-to-integer-atoi/

class Solution {
    public int myAtoi(String s) {
        boolean sign = true;
        int i = 0, length = s.length();
        // 1. Read in and ignore any leading whitespace.
        while (i < length) {
            if (s.charAt(i) != ' ') {
                break;
            }
            i++;
        }

        if (i == length) return 0;

        // 2. Check if the next character is '-' or '+'.
        if (s.charAt(i) == '+') {
            sign = true;
            i++;
        } else if (s.charAt(i) == '-') {
            sign = false;
            i++;
        } else if (!Character.isDigit(s.charAt(i))) {
            return 0;
        }

        // 3. Read in next the characters until the next non-digit character or the end of the input is reached.
        // The rest of the string is ignored.
        // 4. Convert these digits into an integer
        boolean digitFlag = false;
        int digit = 0;
        long result = 0;
        while (i < length && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (long) (s.charAt(i) - '0');
            if (result > 0 || digitFlag) {
                digitFlag = true;
                digit++;
            }
            i++;
        }

        // 5. If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1],
        // then clamp the integer so that it remains in the range.
        if (sign && (digit > 10 || result > Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        } else if (!sign && (digit > 10 || result > Math.abs((long) Integer.MIN_VALUE))) {
            return Integer.MIN_VALUE;
        } else {
            return sign ? (int) result : (int) -result;
        }
    }
}
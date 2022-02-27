// https://leetcode.com/problems/add-binary/

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder num1 = new StringBuilder(a);
        num1.reverse();
        StringBuilder num2 = new StringBuilder(b);
        num2.reverse();

        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(num1.length(), num2.length());
        int sum = 0, carry = 0;
        for (int i = 0; i < maxLength; i++) {
            sum = carry;
            sum += i < num1.length() ? (num1.charAt(i) - '0') : 0;
            sum += i < num2.length() ? (num2.charAt(i) - '0') : 0;

            if (sum % 2 == 1) {
                result.append('1');
            } else {
                result.append('0');
            }
            carry = sum > 1 ? 1 : 0;
        }
        if (carry > 0) result.append('1');

        result.reverse();
        return result.toString();
    }
}
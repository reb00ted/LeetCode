// https://leetcode.com/problems/numbers-at-most-n-given-digit-set/

class Solution {
    int length;
    List<Character> digitList;
    int[] digitsTable;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        this.length = getLength(n);
        this.digitList = Arrays.stream(digits)
                .map(s -> s.charAt(0))
                .distinct()
                .collect(Collectors.toList());

        int result = 0;
        this.digitsTable = new int[length];
        this.digitsTable[0] = 1;
        for (int i = 1; i < length; i++) {
            this.digitsTable[i] = digitsTable[i - 1] * digitList.size();
            result += digitsTable[i];
        }

        String number = String.valueOf(n);
        return result + search(0, number);
    }

    private int getLength(int n) {
        int length = 0;
        while (n > 0) {
            n /= 10;
            length++;
        }
        return length;
    }

    private int search(int i, String number) {
        if (i == number.length()) return 1;

        int result = 0;
        for (char digit : this.digitList) {
            if (digit == number.charAt(i)) {
                result += search(i + 1, number);
            } else if (digit < number.charAt(i)) {
                result += this.digitsTable[length - i - 1];
            }
        }
        return result;
    }
}
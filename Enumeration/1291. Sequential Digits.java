// https://leetcode.com/problems/sequential-digits/

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        for (int start = 1; start <= 9; start++) {
            int number = start;
            for (int j = start + 1; j <= 9 && number <= high; j++) {
                if (low <= number) {
                    result.add(number);
                }
                number = (number * 10) + j;
            }
            if (low <= number && number <= high) {
                result.add(number);
            }
        }

        Collections.sort(result);
        return result;
    }
}
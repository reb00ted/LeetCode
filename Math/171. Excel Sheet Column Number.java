// https://leetcode.com/problems/excel-sheet-column-number/

class Solution {
    public int titleToNumber(String columnTitle) {
        int column = 0;
        for (char ch : columnTitle.toCharArray()) {
            column = (column * 26) + ch - 'A' + 1;
        }
        return column;
    }
}
// https://leetcode.com/problems/detect-capital/

class Solution {
    public boolean detectCapitalUse(String word) {
        if (Character.isLowerCase(word.charAt(0))) {
            return word.codePoints().allMatch(Character::isLowerCase);
        }
        return word.codePoints().skip(1).allMatch(Character::isUpperCase) ||
                word.codePoints().skip(1).allMatch(Character::isLowerCase);
    }
}
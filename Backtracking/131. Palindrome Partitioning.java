// https://leetcode.com/problems/palindrome-partitioning/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        Stack<String> curr = new Stack<>();
        search(s, 0, curr, result);
        return result;
    }

    private void search(String s, int i, Stack<String> curr, List<List<String>> result) {
        if (s.length() == i) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int j = i; j < s.length(); j++) {
            int k = 0;
            boolean isPalindrome = true;
            while (i + k < j - k) {
                if (s.charAt(i + k) != s.charAt(j - k)) {
                    isPalindrome = false;
                    break;
                }
                k++;
            }

            if (isPalindrome) {
                curr.push(s.substring(i, j + 1));
                search(s, j + 1, curr, result);
                curr.pop();
            }
        }
        return;
    }
}
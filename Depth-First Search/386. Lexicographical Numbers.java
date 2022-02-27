// https://leetcode.com/problems/lexicographical-numbers/

class Solution {
    int n;

    public List<Integer> lexicalOrder(int n) {
        this.n = n;

        List<Integer> result = new ArrayList<>(n);
        for (int i = 1; i <= 9; i++) {
            search(result, i);
        }
        return result;
    }

    private void search(List<Integer> result, int curr) {
        if (curr > n) return;

        result.add(curr);
        for (int i = 0; i <= 9; i++) {
            search(result, curr * 10 + i);
        }
        return;
    }
}
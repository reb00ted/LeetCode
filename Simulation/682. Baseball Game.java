// https://leetcode.com/problems/baseball-game/

class Solution {
    public int calPoints(String[] ops) {
        List<Integer> scores = new ArrayList<>();
        for (String op : ops) {
            if (op.equals("C")) {
                scores.remove(scores.size() - 1);
            } else if (op.equals("D")) {
                scores.add(scores.get(scores.size() - 1) * 2);
            } else if (op.equals("+")) {
                int size = scores.size();
                scores.add(scores.get(size - 1) + scores.get(size - 2));
            } else {
                scores.add(Integer.parseInt(op));
            }
        }
        return scores.stream().reduce(0, (a, b) -> a + b);
    }
}
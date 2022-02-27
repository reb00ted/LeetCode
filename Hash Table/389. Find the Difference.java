// https://leetcode.com/problems/find-the-difference/

class Solution {
    public char findTheDifference(String s, String t) {
        Map<Integer, Long> original = s.codePoints().boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Integer, Long> modified = t.codePoints().boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (int key : modified.keySet()) {
            if (!original.containsKey(key) || original.get(key) != modified.get(key)) {
                return (char) key;
            }
        }
        return '\0';
    }
}

class Solution2 {
    public char findTheDifference(String s, String t) {
        return (char) (t.codePoints().sum() - s.codePoints().sum());
    }
}

class Solution3 {
    public char findTheDifference(String s, String t) {
        return (char) (t.codePoints().reduce(0, (a, b) -> a ^ b) ^ s.codePoints().reduce(0, (a, b) -> a ^ b));
    }
}
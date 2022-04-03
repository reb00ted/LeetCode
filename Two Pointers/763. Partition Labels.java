// https://leetcode.com/problems/partition-labels/

class Solution {
    public List<Integer> partitionLabels(String s) {
        ArrayDeque<Set<Character>> partition = new ArrayDeque<>();
        ArrayDeque<Integer> size = new ArrayDeque<>();
        Set<Character> visited = new HashSet<>();

        for (char ch : s.toCharArray()) {
            if (!visited.contains(ch)) {
                visited.add(ch);
                Set<Character> temp = new HashSet<>();
                temp.add(ch);
                partition.push(temp);
                size.push(1);
            } else {
                while (!partition.peek().contains(ch)) {
                    Set<Character> temp = partition.poll();
                    partition.peek().addAll(temp);
                    int tempSize = size.poll();
                    size.push(size.poll() + tempSize);
                }
                size.push(size.poll() + 1);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!size.isEmpty()) {
            result.add(size.pollLast());
        }
        return result;
    }
}

class Solution2 {
    class Solution {
        public List<Integer> partitionLabels(String s) {
            int length = s.length();
            int[] lastIndex = new int[26];
            for (int i = 0; i < length; i++) {
                lastIndex[s.charAt(i) - 'a'] = i;
            }

            List<Integer> result = new ArrayList<>();
            int start = 0, end = 0;
            for (int i = 0; i < length; i++) {
                end = Math.max(end, lastIndex[s.charAt(i) - 'a']);
                if (i == end) {
                    result.add(end - start + 1);
                    start = i + 1;
                }
            }
            return result;
        }
    }
}
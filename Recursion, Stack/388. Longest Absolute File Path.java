// https://leetcode.com/problems/longest-absolute-file-path/

class Solution {
    public int lengthLongestPath(String input) {
        StringBuilder path = new StringBuilder();
        Stack<Integer> indexStack = new Stack<>();
        int longest = 0;

        String[] tokens = input.split("\\n");
        for (String token : tokens) {
            String[] split = token.split("\\t");
            int depth = split.length - 1;
            String currFile = split[depth];

            if (depth == 0) {
                while (!indexStack.isEmpty()) {
                    indexStack.pop();
                }
                path.setLength(0);

                indexStack.push(0);
                path.append(currFile);
            } else {
                int index = -1;
                while (indexStack.size() > depth) {
                    index = indexStack.pop();
                }
                if (index > 0) {
                    path.setLength(index);
                }

                indexStack.push(path.length());
                path.append('/');
                path.append(currFile);
            }

            if (currFile.contains(".")) {
                longest = Math.max(longest, path.length());
            }
        }

        return longest;
    }
}

// 이렇게 경로를 일일이 조립할 필요는 없다. 그냥 길이만 계산하는 것이 더 효율적이다 ㅠ
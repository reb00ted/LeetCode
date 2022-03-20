// https://leetcode.com/problems/simplify-path/

class Solution {
    public String simplifyPath(String path) {
        String[] tokens = path.split("/");
        ArrayDeque<String> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.isEmpty() || token.equals(".")) {
                continue;
            } else if (token.equals("..")) {
                stack.pollLast();
            } else {
                stack.addLast(token);
            }
        }

        return "/" + stack.stream().collect(Collectors.joining("/"));
    }
}
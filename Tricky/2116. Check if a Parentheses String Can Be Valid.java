// https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/

class Solution {
    public boolean canBeValid(String s, String locked) {
        // 길이가 홀수일땐 짝이 안맞는 괄호가 적어도 1개 존재
        if (s.length() % 2 == 1) return false;

        int n = s.length();
        // 앞에서 뒤로 탐색했을 때 매칭이 안되는 닫는 괄호가 있는지 탐색
        // stack 이 0 보다 작아지면 어떤 변환을 하더라도 해당 인덱스의 괄호는 매칭 불가능
        int stack = 0;
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '1') {
                stack += s.charAt(i) == '(' ? 1 : -1;
                if (stack < 0) return false;
            } else {
                stack++;
            }
        }

        // 뒤에서 앞으로 탐색했을 때 매칭이 안되는 여는 괄호가 있는지 탐색
        // stack 이 0 보다 작아지면 어떤 변환을 하더라도 해당 인덱스의 괄호는 매칭 불가능
        stack = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (locked.charAt(i) == '1') {
                stack += s.charAt(i) == ')' ? 1 : -1;
                if (stack < 0) return false;
            } else {
                stack++;
            }
        }

        // 위 두 가지 경우가 아니라면 적절한 변환을 통해서 올바른 매칭 가능
        return true;
    }
}
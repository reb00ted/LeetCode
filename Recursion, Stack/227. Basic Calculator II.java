// https://leetcode.com/problems/basic-calculator-ii/

class Solution {
    public int calculate(String s) {
        Set<Character> primaryOperator = Set.of('*', '/');
        Set<Character> operator = Set.of('+', '-', '*', '/');
        Deque<Integer> numbers = new LinkedList<>();
        Deque<Character> operators = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (operator.contains(ch)) {
                numbers.offerLast(Integer.parseInt(sb.toString()));
                sb.setLength(0);

                // 현재 연산자가 곱셈이나 나눗셈인 경우 계산해서 push
                if (!operators.isEmpty() && primaryOperator.contains(operators.peekLast())) {
                    int num2 = numbers.pollLast(), num1 = numbers.pollLast();
                    char op = operators.pollLast();

                    if (op == '*') {
                        numbers.offerLast(num1 * num2);
                    } else {
                        numbers.offerLast(num1 / num2);
                    }
                }
                operators.offerLast(ch);

            } else if (ch != ' ') {
                sb.append(ch);
            }
        }
        numbers.offerLast(Integer.parseInt(sb.toString()));

        // 마지막 연산자가 곱셈이나 나눗셈인 경우 계산해서 push
        if (!operators.isEmpty() && primaryOperator.contains(operators.peekLast())) {
            int num2 = numbers.pollLast(), num1 = numbers.pollLast();
            char op = operators.pollLast();
            if (op == '*') {
                numbers.offerLast(num1 * num2);
            } else {
                numbers.offerLast(num1 / num2);
            }
        }

        // 남아있는 더하기, 빼기 계산
        while (!operators.isEmpty()) {
            int num1 = numbers.pollFirst(), num2 = numbers.pollFirst();
            char op = operators.pollFirst();
            if (op == '+') {
                numbers.addFirst(num1 + num2);
            } else {
                numbers.addFirst(num1 - num2);
            }
        }
        return numbers.pop();
    }
}


class BetterSolution {
    public int calculate(String s) {
        Set<Character> operators = Set.of('+', '-', '*', '/');
        Stack<Integer> stack = new Stack<>();
        int length = s.length();
        int currentNumber = 0;
        char operation = '+'; // prev 연산자

        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }
            if (operators.contains(ch) || i == length - 1) { // 현재 문자가 연산자이거나, 마지막에 도달한 경우
                if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }

                operation = ch;
                currentNumber = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}


class WithoutStackSolution {
    public int calculate(String s) {
        Set<Character> operators = Set.of('+', '-', '*', '/');
        int length = s.length();
        int currentNumber = 0, prevNumber = 0;
        int result = 0;
        char operation = '+'; // prev 연산자

        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }
            if (operators.contains(ch) || i == length - 1) { // 현재 문자가 연산자이거나, 마지막에 도달한 경우
                if (operation == '+') {
                    result += prevNumber;
                    prevNumber = currentNumber;
                } else if (operation == '-') {
                    result += prevNumber;
                    prevNumber = -currentNumber;
                } else if (operation == '*') {
                    prevNumber *= currentNumber;
                } else if (operation == '/') {
                    prevNumber /= currentNumber;
                }

                operation = ch;
                currentNumber = 0;
            }
        }

        result += prevNumber;
        return result;
    }
}
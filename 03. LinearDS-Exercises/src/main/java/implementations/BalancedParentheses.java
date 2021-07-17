package implementations;

import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    ArrayDeque<Character> stack = new ArrayDeque<>();

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        for (char ch : parentheses.toCharArray()) {
            if (ch == '(')
                stack.push(')');
            else if (ch == '{')
                stack.push('}');
            else if (ch == '[')
                stack.push(']');
            else{
                if (stack.isEmpty() || ch != stack.pop())
                    return false;
            }
        }
        return true;
    }
}

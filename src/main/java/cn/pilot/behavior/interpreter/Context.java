package cn.pilot.behavior.interpreter;


import java.util.HashMap;
import java.util.Stack;

public class Context {
    private Expression expression;

    public void analyse(String exp) {
        Stack<Expression> stack = new Stack<Expression>();

        Expression left;
        Expression right;

        for (int i = 0; i < exp.length(); i++) {
            switch (exp.charAt(i)) {
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(exp.charAt(++i)));
                    // left + right
                    stack.push(new AddExpression(left, right));
                    break;

                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(exp.charAt(++i)));
                    // left - right
                    stack.push(new SubExpression(left, right));
                    break;

                default:
                    stack.push(new VarExpression(String.valueOf(exp.charAt(i))));
            }
        }

        this.expression = stack.pop();
    }

    public int run(HashMap<String, Integer> var) {
        return expression.interpreter(var);
    }
}
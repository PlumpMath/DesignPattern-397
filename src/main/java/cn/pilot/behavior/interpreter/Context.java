package cn.pilot.behavior.interpreter;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private Expression expression;

    private Map<Character, Integer> priorities = new HashMap<>();

    {
        priorities.put(Character.valueOf('+'), 2);
        priorities.put(Character.valueOf('-'), 2);
        priorities.put(Character.valueOf('*'), 3);
        priorities.put(Character.valueOf('/'), 3);
        priorities.put(null, 0);
    }

    public void analyse(String exp) {
        Stack<Character> characters = shuntingYardAlgorithm(exp);
        System.out.println(characters);

        Stack<Expression> expressions = new Stack<>();

        for (Character character : characters) {
            switch (character) {
                case '+':
                    Expression addR = expressions.pop();
                    Expression addL = expressions.pop();
                    expressions.push(new AddExpression(addL, addR));
                    break;
                case '-':
                    Expression minusR = expressions.pop();
                    Expression minusL = expressions.pop();
                    expressions.push(new SubExpression(minusL, minusR));
                    break;
                case '*':
                    Expression multiplyR = expressions.pop();
                    Expression multiplyL = expressions.pop();
                    expressions.push(new MultiplyExpression(multiplyL, multiplyR));
                    break;
                case '/':
                    Expression divideR = expressions.pop();
                    Expression divideL = expressions.pop();
                    expressions.push(new DivideExpression(divideL, divideR));
                    break;
                default:
                    expressions.push(new VarExpression(String.valueOf(character)));
            }
        }

        this.expression = expressions.pop();
    }

    /**
     * http://en.wikipedia.org/wiki/Shunting-yard_algorithm
     * @param exp
     * @return
     */
    private Stack<Character> shuntingYardAlgorithm(String exp) {
        Stack<Character> reExp = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            Character current = Character.valueOf(c);

            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (operators.size() != 0 &&
                            operators.peek() != '(' &&
                            priorities.get(operators.peek()) >= priorities.get(current)) {
                        reExp.push(operators.pop());
                    }
                    operators.push(current);
                    break;

                case '(':
                    operators.push(current);
                    break;

                case ')':
                    while (operators.size() != 0) {
                        if (operators.peek() == '(') {
                            operators.pop();
                        } else {
                            reExp.push(operators.pop());
                        }
                    }
                    break;

                default:
                    reExp.push(c);
            }
        }

        while (operators.size() != 0) {
            reExp.push(operators.pop());
        }

        return reExp;
    }

    public int run(HashMap<String, Integer> var) {
        return expression.interpreter(var);
    }
}
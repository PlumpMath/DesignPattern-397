package cn.pilot.behavior.interpreter;

import java.util.HashMap;

public class MultiplyExpression extends SymbolExpression {
    public MultiplyExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return left.interpreter(var) * right.interpreter(var);
    }
}
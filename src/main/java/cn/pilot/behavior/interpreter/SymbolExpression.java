package cn.pilot.behavior.interpreter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class SymbolExpression implements Expression {
    protected Expression left;
    protected Expression right;
}
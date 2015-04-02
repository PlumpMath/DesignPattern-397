package cn.pilot.behavior.interpreter;

import java.util.HashMap;

public interface Expression {
    int interpreter(HashMap<String, Integer> var);
}
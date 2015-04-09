package cn.pilot.behavior.interpreter;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        HashMap<String, Integer> var = new HashMap<String, Integer>();
        var.put("a", 1);
        var.put("b", 2);
        var.put("c", 3);
        var.put("d", 4);

        Context context = new Context();

        context.analyse("a+b-c+d");
        System.out.println(context.run(var));

        context.analyse("a+b*(c+d)");
        System.out.println(context.run(var));

        context.analyse("(a+b*(c+d))");
        System.out.println(context.run(var));

        context.analyse("(a+b)/c+d");
        System.out.println(context.run(var));

        context.analyse("a*(d/b+c)");
        System.out.println(context.run(var));
    }
}
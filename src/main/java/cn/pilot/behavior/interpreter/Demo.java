package cn.pilot.behavior.interpreter;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        String exp = "a+b-c+d";

        HashMap<String, Integer> var = new HashMap<String, Integer>();
        var.put("a", 1);
        var.put("b", 2);
        var.put("c", 3);
        var.put("d", 4);

        Context context = new Context();

        context.analyse(exp);

        System.out.println(context.run(var));
    }
}
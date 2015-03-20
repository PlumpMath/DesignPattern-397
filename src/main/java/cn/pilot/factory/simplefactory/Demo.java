package cn.pilot.factory.simplefactory;

import cn.pilot.factory.Phone;

public class Demo {
    public static void main(String[] args) {
        Phone nexus = SimpleFactory.createPhone("nexus4");
        nexus.use();

        Phone iphone = SimpleFactory.createPhone("iphone");
        iphone.use();
    }
}
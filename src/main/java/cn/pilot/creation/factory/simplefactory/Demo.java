package cn.pilot.creation.factory.simplefactory;

import cn.pilot.creation.factory.Phone;

public class Demo {
    public static void main(String[] args) {
        Phone nexus = SimpleFactory.createPhone("nexus4");
        nexus.use();

        Phone iphone = SimpleFactory.createPhone("iphone");
        iphone.use();
    }
}
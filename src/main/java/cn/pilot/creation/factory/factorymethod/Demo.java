package cn.pilot.creation.factory.factorymethod;

import cn.pilot.creation.factory.Phone;

public class Demo {
    public static void main(String[] args) {
        PhoneFactory iphoneFactory = new IphoneFactory();
        PhoneFactory nexus4Factory = new Nexus4Factory();

        Phone iphone = iphoneFactory.createPhone();
        Phone nexus4 = nexus4Factory.createPhone();

        iphone.use();
        nexus4.use();
    }
}
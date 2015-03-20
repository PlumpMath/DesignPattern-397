package cn.pilot.factory.abstractfactory;

import cn.pilot.factory.Phone;
import cn.pilot.factory.Tablet;

public class Demo {
    public static void main(String[] args) {
        // apple
        AppleFactory appleFactory = new AppleFactory();
        Phone iphone = appleFactory.createPhone();
        Tablet ipad = appleFactory.createTablet();

        iphone.use();
        ipad.use();

        // google
        GoogleFactory googleFactory = new GoogleFactory();
        Phone nexus4 = googleFactory.createPhone();
        Tablet nexus7 = googleFactory.createTablet();

        nexus4.use();
        nexus7.use();
    }
}
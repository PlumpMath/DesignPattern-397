package cn.pilot.factory.simplefactory;

import cn.pilot.factory.Iphone;
import cn.pilot.factory.Nexus4;
import cn.pilot.factory.Phone;

public class SimpleFactory {

    public static Phone createPhone(String name) {
        switch (name) {
            case "nexus4":
                return new Nexus4();
            case "iphone":
                return new Iphone();
            default:
                return null;
        }
    }
}
package cn.pilot.creation.factory.simplefactory;

import cn.pilot.creation.factory.Iphone;
import cn.pilot.creation.factory.Nexus4;
import cn.pilot.creation.factory.Phone;

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
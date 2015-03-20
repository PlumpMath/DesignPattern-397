package cn.pilot.factory.factorymethod;

import cn.pilot.factory.Nexus4;
import cn.pilot.factory.Phone;

public class Nexus4Factory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new Nexus4();
    }
}
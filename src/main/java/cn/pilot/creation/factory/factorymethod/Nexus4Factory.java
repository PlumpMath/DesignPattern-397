package cn.pilot.creation.factory.factorymethod;

import cn.pilot.creation.factory.Nexus4;
import cn.pilot.creation.factory.Phone;

public class Nexus4Factory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new Nexus4();
    }
}
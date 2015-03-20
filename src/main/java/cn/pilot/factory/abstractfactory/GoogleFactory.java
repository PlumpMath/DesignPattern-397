package cn.pilot.factory.abstractfactory;

import cn.pilot.factory.Nexus4;
import cn.pilot.factory.Nexus7;
import cn.pilot.factory.Phone;
import cn.pilot.factory.Tablet;

public class GoogleFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
        return new Nexus4();
    }

    @Override
    public Tablet createTablet() {
        return new Nexus7();
    }
}
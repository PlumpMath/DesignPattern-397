package cn.pilot.creation.factory.abstractfactory;

import cn.pilot.creation.factory.Nexus4;
import cn.pilot.creation.factory.Nexus7;
import cn.pilot.creation.factory.Phone;
import cn.pilot.creation.factory.Tablet;

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
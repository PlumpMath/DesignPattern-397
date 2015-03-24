package cn.pilot.creation.factory.abstractfactory;

import cn.pilot.creation.factory.Ipad;
import cn.pilot.creation.factory.Iphone;
import cn.pilot.creation.factory.Phone;
import cn.pilot.creation.factory.Tablet;

public class AppleFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
        return new Iphone();
    }

    @Override
    public Tablet createTablet() {
        return new Ipad();
    }
}
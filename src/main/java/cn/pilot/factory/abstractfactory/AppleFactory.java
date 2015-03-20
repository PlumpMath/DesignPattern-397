package cn.pilot.factory.abstractfactory;

import cn.pilot.factory.Ipad;
import cn.pilot.factory.Iphone;
import cn.pilot.factory.Phone;
import cn.pilot.factory.Tablet;

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
package cn.pilot.factory.factorymethod;

import cn.pilot.factory.Iphone;
import cn.pilot.factory.Phone;

public class IphoneFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new Iphone();
    }
}
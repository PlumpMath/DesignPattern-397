package cn.pilot.creation.factory.factorymethod;

import cn.pilot.creation.factory.Iphone;
import cn.pilot.creation.factory.Phone;

public class IphoneFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new Iphone();
    }
}
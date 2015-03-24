package cn.pilot.creation.factory.abstractfactory;

import cn.pilot.creation.factory.Phone;
import cn.pilot.creation.factory.Tablet;

public interface AbstractFactory {

    Phone createPhone();

    Tablet createTablet();
}
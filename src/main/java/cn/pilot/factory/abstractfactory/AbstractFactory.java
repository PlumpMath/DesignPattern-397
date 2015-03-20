package cn.pilot.factory.abstractfactory;

import cn.pilot.factory.Phone;
import cn.pilot.factory.Tablet;

public interface AbstractFactory {

    Phone createPhone();

    Tablet createTablet();
}
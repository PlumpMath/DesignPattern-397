package cn.pilot.structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * in reality, this should be modified to support concurrent purpose
 */
public class GadgetFlyweightFactory {
    private Map<String, Gadget> gadgets = new HashMap<>();

    {
        gadgets.put("menu", new Menu());
        gadgets.put("listview", new ListView());
    }

    public Gadget getGadget(String name) {
        return gadgets.get(name);
    }
}
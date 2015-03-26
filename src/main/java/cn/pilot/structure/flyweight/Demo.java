package cn.pilot.structure.flyweight;

public class Demo {
    public static void main(String[] args) {
        GadgetFlyweightFactory gadgetFlyweightFactory = new GadgetFlyweightFactory();

        Gadget menu1 = gadgetFlyweightFactory.getGadget("menu");
        Gadget menu2 = gadgetFlyweightFactory.getGadget("menu");
        Gadget menu3 = gadgetFlyweightFactory.getGadget("menu");

        System.out.println(menu1);
        System.out.println(menu2);
        System.out.println(menu3);
    }
}
package cn.pilot.behavior.nullobject;

public class Demo {
    public static void main(String[] args) {
        Factory.getService("api").execute();

        Factory.getService("n/a").execute();
    }
}
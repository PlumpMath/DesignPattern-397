package cn.pilot.behavior.nullobject;

public class NullService implements Service {
    @Override
    public void execute() {
        System.out.println("do nothing");
    }
}
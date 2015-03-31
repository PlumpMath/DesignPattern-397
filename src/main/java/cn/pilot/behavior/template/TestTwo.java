package cn.pilot.behavior.template;

public class TestTwo extends EndToEndTest {
    @Override
    protected void setup() {
        System.out.println("TestTwo: setup");
    }

    @Override
    protected void verify() {
        System.out.println("TestTwo: verify");
    }
}
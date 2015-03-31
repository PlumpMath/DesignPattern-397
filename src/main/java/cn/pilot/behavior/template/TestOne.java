package cn.pilot.behavior.template;

public class TestOne extends EndToEndTest {
    @Override
    protected void setup() {
        System.out.println("TestOne: setup");
    }

    @Override
    protected void verify() {
        System.out.println("TestOne: verify");
    }
}
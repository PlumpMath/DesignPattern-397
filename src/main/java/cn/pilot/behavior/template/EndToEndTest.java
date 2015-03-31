package cn.pilot.behavior.template;

public abstract class EndToEndTest {
    public void test() {
        setup();
        verify();
        reset();
    }

    abstract protected void setup();
    abstract protected void verify();

    // subclass can still override the default reset() method
    protected void reset() {
        System.out.println("Reset!");
    }
}
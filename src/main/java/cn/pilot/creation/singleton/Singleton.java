package cn.pilot.creation.singleton;

public class Singleton {
    // inner static class will only be loaded when it is called
    private static class SingletonHolder {
        private static final OneInstance INSTANCE = new OneInstance();
    }

    private Singleton() {
    }

    public static final OneInstance getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
package cn.pilot.singleton;

public class LazySingleton {
    private static OneInstance oneInstance;

    private LazySingleton() {
    }

    // synchronized make sure only one thread is calling getInstance() method
    // since two thread may access oneInstace=null at same time
    public static synchronized OneInstance getInstance() {
        if (oneInstance == null) {
            oneInstance = new OneInstance();
        }
        return oneInstance;
    }
}
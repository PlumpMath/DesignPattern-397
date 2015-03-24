package cn.pilot.creation.singleton;

public class DoubleCheckedLazySingleton {
    private volatile static OneInstance oneInstance;

    private DoubleCheckedLazySingleton() {
    }

    public static OneInstance getInstance() {
        // check first since most time oneInstance is not null, and we don't need to acquire lock for read operation
        if (oneInstance == null) {
            synchronized (DoubleCheckedLazySingleton.class) {
                if (oneInstance == null) {
                    oneInstance = new OneInstance();
                }
            }
        }
        return oneInstance;
    }
}
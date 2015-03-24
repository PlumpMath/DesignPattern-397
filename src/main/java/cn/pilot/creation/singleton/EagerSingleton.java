package cn.pilot.creation.singleton;

public class EagerSingleton {
    // initialize first when class is loaded
    // there is no problem for concurrent but the object will remain in the memory all the time
    private static OneInstance oneInstance = new OneInstance();

    private EagerSingleton() {
    }

    public static OneInstance getInstance() {
        return oneInstance;
    }
}
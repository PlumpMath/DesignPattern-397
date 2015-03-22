package cn.pilot.singleton;

public enum SingletonEnum {
    INSTANCE;

    public void method(){
        // only INSTANCE can access this method
    }
}
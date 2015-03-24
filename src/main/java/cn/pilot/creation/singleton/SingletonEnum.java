package cn.pilot.creation.singleton;

public enum SingletonEnum {
    INSTANCE;

    public void method(){
        // only INSTANCE can access this method
    }
}
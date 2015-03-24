package cn.pilot.creation.singleton;

/**
 * Created by T800 on 3/22/15.
 */
public class Demo {
    public static void main(String[] args) {
        SingletonEnum.INSTANCE.method();
        SingletonEnum.INSTANCE.method();
        SingletonEnum.INSTANCE.method();
        SingletonEnum.INSTANCE.method();
    }
}
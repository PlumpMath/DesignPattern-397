package cn.pilot.idiom.poisonpill;

public interface MQSubscribePoint {
    Message take() throws InterruptedException;
}
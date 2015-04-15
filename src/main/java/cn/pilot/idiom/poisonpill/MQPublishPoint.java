package cn.pilot.idiom.poisonpill;

public interface MQPublishPoint {
    void send(Message message) throws InterruptedException;
}
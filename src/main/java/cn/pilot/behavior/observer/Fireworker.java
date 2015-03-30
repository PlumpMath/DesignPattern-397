package cn.pilot.behavior.observer;

public class Fireworker implements AlarmSubscriber {
    @Override
    public void receiveAlarm(String address) {
        System.out.printf("Fireworker receives alarm from %s\n", address);
    }
}
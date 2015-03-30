package cn.pilot.behavior.observer;

public class Resident implements AlarmSubscriber {
    @Override
    public void receiveAlarm(String address) {
        System.out.printf("Resident receives alarm from %s\n", address);
    }
}
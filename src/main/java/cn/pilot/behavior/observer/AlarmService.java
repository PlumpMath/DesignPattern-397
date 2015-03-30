package cn.pilot.behavior.observer;

import java.util.ArrayList;
import java.util.List;

public class AlarmService {
    // in reality, concurrency ability should be added for subscribers/observers
    private List<AlarmSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(AlarmSubscriber alarmSubscriber) {
        subscribers.add(alarmSubscriber);
    }

    public void soundAlarm(String address) {
        notifyAllSubscribers(address);
    }

    private void notifyAllSubscribers(String address) {
        for (AlarmSubscriber subscriber : subscribers) {
            subscriber.receiveAlarm(address);
        }
    }
}
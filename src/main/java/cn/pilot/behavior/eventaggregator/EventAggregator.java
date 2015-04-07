package cn.pilot.behavior.eventaggregator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventAggregator {
    private static EventAggregator instance = new EventAggregator();

    private EventAggregator() {
    }

    private Map<Event, List<Subscriber>> handlers = new HashMap<>();

    public static EventAggregator getInstance() {
        return instance;
    }

    public void subscribe(Event event, Subscriber subscriber) {
        List<Subscriber> subscribers = handlers.get(event);
        if (subscribers == null) {
            List<Subscriber> newSubscribers = new ArrayList<>();
            newSubscribers.add(subscriber);
            handlers.put(event, newSubscribers);
        } else {
            subscribers.add(subscriber);
        }
    }

    public void publish(Event event) {
        List<Subscriber> subscribers = handlers.get(event);
        for (Subscriber subscriber : subscribers) {
            subscriber.onEvent(event);
        }
    }

}
package cn.pilot.behavior.eventaggregator;

public interface Subscriber {
    void onEvent(Event event);
}
package cn.pilot.behavior.eventaggregator;

public class Demo {
    public static void main(String[] args) {
        // event handlers
        Subscriber fireworker = new Fireworker();
        Subscriber resident = new Resident();
        Subscriber police = new Police();

        // event aggregator
        EventAggregator eventAggregator = EventAggregator.getInstance();

        // subscribing to a single place
        eventAggregator.subscribe(Event.FIRE, fireworker);
        eventAggregator.subscribe(Event.FIRE, resident);

        eventAggregator.subscribe(Event.ROBBERY, police);
        eventAggregator.subscribe(Event.ROBBERY, resident);

        eventAggregator.subscribe(Event.NEW_YEAR, resident);

        // event is triggered
        eventAggregator.publish(Event.FIRE);
        System.out.println("=========================");

        eventAggregator.publish(Event.NEW_YEAR);
        System.out.println("=========================");

        eventAggregator.publish(Event.ROBBERY);
        System.out.println("=========================");
    }
}
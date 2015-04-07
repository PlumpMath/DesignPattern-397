package cn.pilot.behavior.eventaggregator;

public class Fireworker implements Subscriber {
    @Override
    public void onEvent(Event event) {
        if (event == Event.FIRE) {
            fireHandler();
        }
    }

    private void fireHandler() {
        System.out.println("Firework handles fire event");
    }
}
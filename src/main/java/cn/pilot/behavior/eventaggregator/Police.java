package cn.pilot.behavior.eventaggregator;

public class Police implements Subscriber {
    @Override
    public void onEvent(Event event) {
        if (event == Event.ROBBERY) {
            robberyHandler();
        }
    }

    private void robberyHandler() {
        System.out.println("Police handles robbery event");
    }
}
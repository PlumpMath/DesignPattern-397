package cn.pilot.behavior.eventaggregator;

public class Resident implements Subscriber {
    @Override
    public void onEvent(Event event) {
        switch (event) {
            case FIRE:
                fireHandler();
                break;
            case ROBBERY:
                robberyHandler();
                break;
            case NEW_YEAR:
                newyearHandler();
                break;
        }
    }

    private void fireHandler() {
        System.out.println("Resident knows fire event");
    }

    private void robberyHandler() {
        System.out.println("Resident knows robbery event");
    }

    private void newyearHandler() {
        System.out.println("Resident receives new year congratulations");
    }
}
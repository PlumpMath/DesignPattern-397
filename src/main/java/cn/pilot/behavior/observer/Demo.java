package cn.pilot.behavior.observer;

public class Demo {
    public static void main(String[] args) {
        AlarmSubscriber fireworker = new Fireworker();
        AlarmSubscriber resident = new Resident();

        // register
        AlarmService alarmService = new AlarmService();
        alarmService.addSubscriber(fireworker);
        alarmService.addSubscriber(resident);

        // event
        alarmService.soundAlarm("Moon Road");
    }
}
package cn.pilot.behavior.command;

public class Demo {
    public static void main(String[] args) {
        // receiver
        Light light = new Light();

        // Commands
        Command switchUp = new FlipUpCommand(light);
        Command switchDown = new FlipDownCommand(light);

        // invoker
        Switch theSwitch = new Switch();

        theSwitch.storeAndExecute(switchUp);
        theSwitch.storeAndExecute(switchDown);
    }
}
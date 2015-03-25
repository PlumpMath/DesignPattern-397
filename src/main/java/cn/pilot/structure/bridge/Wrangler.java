package cn.pilot.structure.bridge;

public class Wrangler extends Jeep {
    public Wrangler(Engine engine) {
        super(engine);
    }

    @Override
    public void start() {
        engine.start();
    }

    public void switchPowerTrain() {
        System.out.println("wrangler switch power train");
    }
}
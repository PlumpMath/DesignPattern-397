package cn.pilot.structure.bridge;

public class Renegade extends Jeep {
    public Renegade(Engine engine) {
        super(engine);
    }

    @Override
    public void start() {
        engine.start();
    }

    public void openRoof() {
        System.out.println("renegade open roof");
    }
}
package cn.pilot.structure.bridge;

public abstract class Jeep {
    protected Engine engine;

    public Jeep(Engine engine) {
        this.engine = engine;
    }

    public abstract void start();
}
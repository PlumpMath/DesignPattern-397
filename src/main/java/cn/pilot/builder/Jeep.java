package cn.pilot.builder;

public class Jeep {
    Engine engine;
    PowerTrain powerTrain;

    // only allow JeepBuilder to create Jeep
    private Jeep(JeepBuilder jeepBuilder) {
        this.engine = jeepBuilder.engine;
        this.powerTrain = jeepBuilder.powerTrain;
    }

    public static class JeepBuilder {
        Engine engine;
        PowerTrain powerTrain;
        String name;

        public JeepBuilder(String name) {
            this.name = name;
        }

        public JeepBuilder withEngine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public JeepBuilder withPowerTrain(PowerTrain powerTrain) {
            this.powerTrain = powerTrain;
            return this;
        }

        public Jeep build() {
            return new Jeep(this);
        }
    }
}
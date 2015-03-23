package cn.pilot.builder;

import cn.pilot.builder.Jeep.JeepBuilder;

public class Demo {
    public static void main(String[] args) {
        // wrangler
        Jeep wrangler = new JeepBuilder("wrangler")
                .withEngine(new V6Engine()).withPowerTrain(new FourWheelDrive())
                .build();

        // renegade
        Jeep renegade = new JeepBuilder("renegade")
                .withEngine(new TurboEngine())
                .withPowerTrain(new TwoWheelDrive())
                .build();
    }
}
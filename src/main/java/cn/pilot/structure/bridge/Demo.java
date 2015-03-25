package cn.pilot.structure.bridge;

/**
 * 3 kinds of engines and 2 kinds of car --> 2 * 3 = 6 objects
 * With bridge pattern, 2 + 3 = 5 objects can represent all 6 objects
 */
public class Demo {
    public static void main(String[] args) {
        // wrangler with diesel
        Wrangler dieselWrangler = new Wrangler(new DieselEngine());
        dieselWrangler.start();
        // its own method
        dieselWrangler.switchPowerTrain();

        // wrangler with penatastar
        Wrangler v6Wranger = new Wrangler(new PentastarEngine());
        v6Wranger.start();
        v6Wranger.switchPowerTrain();

        // renegade
        Renegade renegade = new Renegade(new TurboEngine());
        renegade.start();
        renegade.openRoof();
    }
}
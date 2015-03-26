package cn.pilot.structure.decorator;

public class ModifiedJeep extends Jeep{

    private Jeep jeep;

    public ModifiedJeep(Jeep jeep) {
        this.jeep = jeep;
    }

    @Override
    void drive() {
        System.out.println("Jeep is modified! Speed up!");
        jeep.drive();
    }
}
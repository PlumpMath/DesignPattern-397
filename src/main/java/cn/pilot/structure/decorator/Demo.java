package cn.pilot.structure.decorator;

public class Demo {
    public static void main(String[] args) {
        Jeep wrangler = new Wrangler();
        wrangler.drive();

        // after decoration, still use Jeep as declaration
        Jeep decorator = new ModifiedJeep(new Wrangler());
        decorator.drive();
    }
}
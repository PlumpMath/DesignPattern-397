package cn.pilot.structure.proxy;

public class Demo {
    public static void main(String[] args) {
        DataService service = new DataServiceImplProxy();

        service.save();
    }
}
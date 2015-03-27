package cn.pilot.structure.proxy;

public class DataServiceImplProxy extends DataServiceImpl {
    @Override
    public void save() {
        System.out.println("start transaction");
        super.save();
        System.out.println("commit; end transaction");
    }
}
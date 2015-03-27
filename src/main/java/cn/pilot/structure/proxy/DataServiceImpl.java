package cn.pilot.structure.proxy;

public class DataServiceImpl implements DataService {
    @Override
    public void save() {
        System.out.println("call db operation");
    }
}
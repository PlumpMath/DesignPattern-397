package cn.pilot.structure.composite;

public class Exec extends File {
    public Exec(String name) {
        super("[Exec]:" + name);
    }

    @Override
    void delete() {
        System.out.printf("Delete:%s\n", name);
    }

    @Override
    void addFile(File file) {
        throw new RuntimeException("This method is not supposed to be called by this object");
    }
}
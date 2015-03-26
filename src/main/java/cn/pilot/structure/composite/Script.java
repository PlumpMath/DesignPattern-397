package cn.pilot.structure.composite;

public class Script extends File {
    public Script(String name) {
        super("[script]" + name);
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
package cn.pilot.structure.composite;

import java.util.ArrayList;

public class Directory extends File {
    private ArrayList<File> files = new ArrayList<>();

    public Directory(String name) {
        super("[directory]" + name);
    }

    @Override
    void delete() {
        System.out.printf("Deleting:%s\n", name);
        for (File file : files) {
            file.delete();
        }
        System.out.printf("Deletion is done");
    }

    @Override
    void addFile(File file) {
        files.add(file);
    }
}
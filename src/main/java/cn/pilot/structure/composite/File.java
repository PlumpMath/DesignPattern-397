package cn.pilot.structure.composite;

/**
 * composite extract all public methods to parent, but some kinds of subclass actually don't need it
 * this is a compromise to create one object to represent Tree
 */
public abstract class File {
    protected String name;

    public File(String name) {
        this.name = name;
    }

    abstract void delete();

    abstract void addFile(File file);
}
package cn.pilot.structure.composite;

public class Demo {
    public static void main(String[] args) {
        File script = new Script("shell.sh");
        File exec = new Exec("game.exe");
        File directory = new Directory("home");

        directory.addFile(script);
        directory.addFile(exec);

        directory.delete();
    }
}
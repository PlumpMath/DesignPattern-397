package cn.pilot.behavior.chain.logger;

public class StderrLogger extends Logger {
    protected StderrLogger(int priority) {
        super(priority);
    }

    @Override
    void writeMessage(String msg) {
        System.out.printf("Writing to stderr: %s\n", msg);
    }
}
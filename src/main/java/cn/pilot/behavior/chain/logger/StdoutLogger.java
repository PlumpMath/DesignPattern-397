package cn.pilot.behavior.chain.logger;

public class StdoutLogger extends Logger {
    protected StdoutLogger(int priority) {
        super(priority);
    }

    @Override
    void writeMessage(String msg) {
        System.out.printf("Writing to stdout: %s\n", msg);
    }
}
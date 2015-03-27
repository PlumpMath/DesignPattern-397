package cn.pilot.behavior.chain.logger;

public class EmailLogger extends Logger {
    protected EmailLogger(int priority) {
        super(priority);
    }

    @Override
    void writeMessage(String msg) {
        System.out.printf("Sending via email: %s\n", msg);
    }
}
package cn.pilot.behavior.chain.logger;

public abstract class Logger {
    public final static int ERR = 3;
    public final static int NOTICE = 5;
    public final static int DEBUG = 7;

    private int mask;

    private Logger next;

    protected Logger(int mask) {
        this.mask = mask;
    }

    public void setNext(Logger next) {
        this.next = next;
    }

    public void message(String msg, int priority) {
        if (priority <= mask) {
            writeMessage(msg);
        }
        if (next != null) {
            next.message(msg, priority);
        }
    }

    abstract void writeMessage(String msg);
}
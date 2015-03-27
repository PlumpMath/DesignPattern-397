package cn.pilot.behavior.chain.logger;

public class Demo {
    public static void main(String[] args) {
        Logger email = new EmailLogger(Logger.NOTICE);
        Logger stderr = new StderrLogger(Logger.ERR);
        Logger stdout = new StdoutLogger(Logger.DEBUG);

        // actually the sequence is not important here
        email.setNext(stderr);
        stderr.setNext(stdout);

        // chain is created
        email.message("Entering function y", Logger.DEBUG);
        email.message("Step1 completed", Logger.NOTICE);
        email.message("An error has occurred", Logger.ERR);
    }
}
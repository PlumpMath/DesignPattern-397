package cn.pilot.behavior.mediator;

public class Receiver{
    private MailService mailService;

    public Receiver(MailService mailService) {
        this.mailService = mailService;
    }

    public void receive(String message) {
        System.out.printf("Show message: %s\n", message);
    }
}
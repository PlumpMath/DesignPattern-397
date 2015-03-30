package cn.pilot.behavior.mediator;

public class Demo {
    public static void main(String[] args) {
        MailService mailService = new MailService();

        Sender sender = new Sender(mailService);
        Receiver receiver = new Receiver(mailService);

        mailService.setSender(sender);
        mailService.setReceiver(receiver);

        sender.send();
    }
}
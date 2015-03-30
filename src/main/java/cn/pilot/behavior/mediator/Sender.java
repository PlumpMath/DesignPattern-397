package cn.pilot.behavior.mediator;

public class Sender {
    private MailService mailService;

    public Sender(MailService mailService) {
        this.mailService = mailService;
    }

    public void send() {
        mailService.senderSendMessage("send");
    }
}
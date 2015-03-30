package cn.pilot.behavior.mediator;

import lombok.Setter;

@Setter
public class MailService {
    private Sender sender;

    private Receiver receiver;

    public void senderSendMessage(String message) {
        System.out.println("Mediator: I am in charge of how sender and receiver connect");
        System.out.println("Message will be sent tomorrow");
        receiver.receive(message);
    }
}
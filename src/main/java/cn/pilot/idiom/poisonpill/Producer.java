package cn.pilot.idiom.poisonpill;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Producer {
    private MQPublishPoint queue;

    public void sendBody(String body) {
        Message msg = new SimpleMessage();
        msg.addHeader(Message.Header.DATE, new Date().toString());
        msg.setBody(body);

        try {
            queue.send(msg);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void send(Message message) {
        try {
            queue.send(message);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
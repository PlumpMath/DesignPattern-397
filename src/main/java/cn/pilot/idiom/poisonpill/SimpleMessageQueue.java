package cn.pilot.idiom.poisonpill;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleMessageQueue implements MessageQueue {
    private final BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(100);

    @Override
    public void send(Message message) throws InterruptedException {
        queue.put(message);
    }

    @Override
    public Message take() throws InterruptedException {
        return queue.take();
    }
}
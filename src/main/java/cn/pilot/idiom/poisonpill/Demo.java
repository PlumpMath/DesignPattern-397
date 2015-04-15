package cn.pilot.idiom.poisonpill;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {

    public static void main(String[] args) {
        final MessageQueue queue = new SimpleMessageQueue();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<?> consumerA = executorService.submit(new Runnable() {
            @Override
            public void run() {
                String name = "Consumer 01";

                new Consumer(name, queue).consume();

                System.out.printf("$ %s is stopped\n", name);
            }
        });

        Future<?> consumerB = executorService.submit(new Runnable() {
            @Override
            public void run() {
                String name = "Consumer 02";

                new Consumer(name, queue).consume();

                System.out.printf("$ %s is stopped\n", name);
            }
        });

        Future<?> producer = executorService.submit(new Runnable() {
            @Override
            public void run() {
                Producer producer = new Producer(queue);

                producer.sendBody("1st message");
                producer.sendBody("2st message");
                producer.send(Message.POISON_PILL);
                producer.send(Message.POISON_PILL);

                System.out.println("$ Producer is stopped");
            }
        });

        // wait for all tasks are finished
        while (true) {
            if (consumerA.isDone() && consumerB.isDone() && producer.isDone()) {
                executorService.shutdownNow();
                return;
            }
        }
    }
}
package cn.pilot.idiom.poisonpill;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Consumer {
    private String name;
    private MQSubscribePoint queue;

    public void consume() {
        while (true) {
            try {
                Message msg = queue.take();

                // POISON_PILL is a final field inside Message, its reference (address) is always the same
                if (msg == Message.POISON_PILL) {
                    System.out.printf("Consumer (%s) receives POISON PILL and is going to be stopped\n", this.name);
                    return;
                }

                System.out.printf("[%s] Consumer (%s) receives message: %s\n", msg.getHeader(Message.Header.DATE), this.name, msg.getBody());
            } catch (InterruptedException e) {
                System.err.println(e);
                return;
            }
        }
    }
}
package practice.pubsub;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SleepingSubscriber implements ISubscriber {
    private String id;
    private final int sleepTimeInMillis;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Starting to consume : " + message.getMessage());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Done consuming : " + message.getMessage());
    }
}

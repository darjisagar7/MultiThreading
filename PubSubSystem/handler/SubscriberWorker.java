package practice.pubsub;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicSubscriber) {
            while (true) {
                final int currOffset = topicSubscriber.getOffset().get();
                while (currOffset >= topic.getMessages().size()) {
                    topicSubscriber.wait();
                }
                Message message = topic.getMessages().get(currOffset);
                topicSubscriber.getSubscriber().consume(message);

                topicSubscriber.getOffset().compareAndSet(currOffset, currOffset + 1);
            }
        }
    }
}

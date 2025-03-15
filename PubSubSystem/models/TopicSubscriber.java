package practice.pubsub;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TopicSubscriber {

    private final ISubscriber subscriber;
    private final AtomicInteger offset;

    public TopicSubscriber(ISubscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }
}

package practice.pubsub;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Topic {
    private String topicName;
    private String id;
    private List<Message> messages;
    private List<TopicSubscriber> subscribers;

    public void addSubscriber(TopicSubscriber topicSubscriber) {
        subscribers.add(topicSubscriber);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}

package practice.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageQueue {
    private final Map<String, TopicHandler> topicProcessors;

    public MessageQueue() {
        this.topicProcessors = new HashMap<>();
    }

    public Topic createTopic(String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString(), new ArrayList<>(), new ArrayList<>());
        final TopicHandler topicHandler = new TopicHandler(topic);
        topicProcessors.put(topic.getId(), topicHandler);
        System.out.println("Created topic: " + topic.getTopicName());
        return topic;
    }

    public void subscribe(final ISubscriber subscriber, final Topic topic) {
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish(Topic topic, Message message) {
        topic.addMessage(message);
        System.out.println(message.getMessage() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicProcessors.get(topic.getId()).publish()).start();
    }
}

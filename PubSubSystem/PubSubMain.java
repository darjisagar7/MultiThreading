package practice.pubsub;

class PubSubMain {

    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        Topic topic1 = messageQueue.createTopic("topic1");
        Topic topic2 = messageQueue.createTopic("topic2");

        final SleepingSubscriber sub1 = new SleepingSubscriber("sub1", 10000);
        final SleepingSubscriber sub2 = new SleepingSubscriber("sub2", 10000);

        messageQueue.subscribe(sub1, topic1);
        messageQueue.subscribe(sub2, topic1);

        final SleepingSubscriber sub3 = new SleepingSubscriber("sub3", 10000);

        messageQueue.subscribe(sub3, topic2);

        messageQueue.publish(topic1, new Message("m1"));
        messageQueue.publish(topic1, new Message("m2"));

        messageQueue.publish(topic2, new Message("m3"));

        Thread.sleep(15000);
        messageQueue.publish(topic2, new Message("m4"));
        messageQueue.publish(topic1, new Message("m5"));
    }
}

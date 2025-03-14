package practice.pubsub;

interface ISubscriber {

    String getId();
    void consume(Message message) throws InterruptedException;
}

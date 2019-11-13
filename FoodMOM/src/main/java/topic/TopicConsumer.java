package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicConsumer {
    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "test_topic";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        TopicMessageHandler m = new TopicMessageHandler();
        m.getCategories(channel, QUEUE_NAME);
        String categoryChoice = "Nuts"; //the category string that decides the food names
        m.pickCategory(categoryChoice, EXCHANGE_NAME, channel);
    }

}

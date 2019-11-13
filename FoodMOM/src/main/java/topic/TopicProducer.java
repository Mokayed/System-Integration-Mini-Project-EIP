package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import data.CSVHandler;
import data.JSONHandler;
import entities.Food;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TopicProducer {
    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "test_topic";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        List<Food> foods = new ArrayList<>();
        CSVHandler c = new CSVHandler();
        JSONHandler j = new JSONHandler();
        c.readCsv(foods);
        j.readJson(foods);
        foods.remove(0);
        for (Food food : foods) {
            System.out.println(food.toString());
        }
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "";
            for (Food food : foods) {
                if (!message.contains(food.getFood_category())) {

                    message += food.getFood_category() + "\n";
                }
            }
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            try (Connection connection2 = factory.newConnection();
                 Channel channel2 = connection2.createChannel()) {
                channel2.exchangeDeclare(EXCHANGE_NAME, "topic");
                for (Food f : foods) {
                    channel.basicPublish(EXCHANGE_NAME, f.getFood_category(), null, (f.getName()/* + "," + f.getDescription()*/).getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }
}

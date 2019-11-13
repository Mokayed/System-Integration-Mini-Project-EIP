package rpc;

import com.rabbitmq.client.*;
import data.CSVHandler;
import data.JSONHandler;
import entities.Food;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Replier {
    private static final String RPC_QUEUE_NAME = "rpc_queue";
    private static final String RPC_EXCHANGE_NAME = "rpc_exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        List<Food> foods = new ArrayList<>();
        CSVHandler c = new CSVHandler();
        JSONHandler j = new JSONHandler();
        c.readCsv(foods);
        j.readJson(foods);
        foods.remove(0);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(RPC_QUEUE_NAME);
            channel.basicQos(1);
            System.out.println(" [x] Awaiting RPC requests");
            Object monitor = new Object();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();
                String response = "";
                try {
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    MessageHandler m = new MessageHandler();
                    response = m.handleMessages(message, foods);
                    System.out.println(" [.] (" + message + ")");
                } catch (RuntimeException e) {
                    System.out.println(" [.] " + e.toString());
                } finally {
                    //channel.exchangeDeclare("topic");
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes(StandardCharsets.UTF_8));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {
            }));
            // Wait and be prepared to consume the message from RPC client.
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
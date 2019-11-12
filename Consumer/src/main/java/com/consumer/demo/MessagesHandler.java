package com.consumer.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MessagesHandler {

  public  String getCategories(Channel channel, String QUEUE_NAME) throws IOException {
      ArrayList<String> categories = new ArrayList<>();
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), "UTF-8");
          System.out.println(" [x] Received " + message + "");
      };
      channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
      String categoriesString = "";
      for (String category: categories){
          categoriesString+=categories;
      }
return categoriesString;
    }

    public String pickCategory(String category, String EXCHANGE_NAME, Channel channel) throws IOException {
        ArrayList<String> foodNames = new ArrayList<>();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, category + ".#");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            foodNames.add(message);
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback2, consumerTag -> { });
        String categoriesString = "";
        for (String food: foodNames){
            System.out.println(category);
            categoriesString+=food;
        }
        return categoriesString;
    }
}

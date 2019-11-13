package com.consumer.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class MessagesHandler {

  public void getCategories(Channel channel, String QUEUE_NAME) throws IOException, InterruptedException {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), "UTF-8");
          System.out.println(message + "please pick one of the food options above");
      };
      channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {

      });
    }

    public void pickCategory(String category, String EXCHANGE_NAME, Channel channel) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("you typed: " + input);
        channel.queueBind(queueName, EXCHANGE_NAME, input + ".#");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };



        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}

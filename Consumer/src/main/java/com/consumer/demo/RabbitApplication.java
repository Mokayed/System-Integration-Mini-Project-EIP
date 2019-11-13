package com.consumer.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;


@SpringBootApplication
public class RabbitApplication
{
    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "test_topic";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        //System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        MessagesHandler m = new MessagesHandler();
        m.getCategories(channel, QUEUE_NAME);

    m.pickCategory("qweqw", EXCHANGE_NAME, channel);






//while(true){



    /*    switch (categoryChosen){

            case "b":
                String categoriesChosen = m.pickCategory("Nuts", EXCHANGE_NAME, channel);
                System.out.println(categoriesChosen);
                break;
            case "a":
                String categories = m.getCategories(channel, QUEUE_NAME);
                System.out.println(categories);
                break;

        }*/

//}


       //m.pickCategory("", EXCHANGE_NAME, channel);

/*

*/
    }
}
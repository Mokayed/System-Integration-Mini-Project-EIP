package com.consumer.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class ThreadA {



    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "test_topic";


    public static void main(String... args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        ThreadB b = new ThreadB(channel, QUEUE_NAME);
        b.start();

        synchronized (b) {
            while (b.sum == 0) {
                System.out.println("waiting for thread b ...");
                b.wait();
            }

            System.out.println("b has completed!");
        }
    }
}

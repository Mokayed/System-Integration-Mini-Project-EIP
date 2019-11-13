package com.consumer.demo;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public class ThreadB extends Thread{
    int sum;
    private Channel channel;
    private String QUEUE_NAME;

    ThreadB(Channel channel, String queueName){
        this.channel = channel;
        this.QUEUE_NAME = queueName;

    }

    @Override
    public void run() {
        synchronized (this) {
            MessagesHandler m = new MessagesHandler();
            try {
                m.getCategories(channel, QUEUE_NAME);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }
    }
}

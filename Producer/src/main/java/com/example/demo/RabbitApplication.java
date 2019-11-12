package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.RpcClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RabbitApplication {
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
            for (Food food : foods){
            if(!message.contains(food.getFood_category())){

                message+=food.getFood_category()+ "\n";
            }
            }



            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            //System.out.println(" [x] Sent '" + message + "'");

        try (Connection connection2 = factory.newConnection();
             Channel channel2 = connection.createChannel()) {



            channel.exchangeDeclare(EXCHANGE_NAME, "topic");


            for (Food f : foods){
                channel.basicPublish(EXCHANGE_NAME, f.getFood_category(), null, (f.getName()/* + "," + f.getDescription()*/).getBytes("UTF-8"));
            }

            //String[] pizzaNames = {"Margerita, Pepperoni, Hawaii"};




                /*if(pizza.equals("Margerita")){
                    channel.basicPublish(EXCHANGE_NAME, "cheese.tomato", null, pizza.getBytes("UTF-8"));
                    System.out.println(" [x] Sent '" + "cheese.tomato" + "':'" + pizza + "'");
                }
                if(pizza.equals("Pepperoni")){
                    channel.basicPublish(EXCHANGE_NAME, "cheese.tomato.pepperoni", null, pizza.getBytes("UTF-8"));
                    System.out.println(" [x] Sent '" + "cheese.tomato.pepperoni" + "':'" + pizza + "'");
                }
                if(pizza.equals("Hawaii")){
                    channel.basicPublish(EXCHANGE_NAME, "cheese.tomato.pineapple", null, pizza.getBytes("UTF-8"));
                    System.out.println(" [x] Sent '" + "cheese.tomato.pineapple" + "':'" + pizza + "'");
                }*/
           /* channel.basicPublish(EXCHANGE_NAME, "cheese.tomato", null, "Margerita".getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME, "cheese.tomato.pepperoni", null, "Pepperoni".getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME, "cheese.tomato.pineapple", null, "Hawaii".getBytes(StandardCharsets.UTF_8));
*/
            //String routingKey = "cheese.pepperoni";
            //String message = "pepperoni pizza";

            //channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            //System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }


        /*try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello she!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }*/


    }
}}
    /*
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitApplication.class, args);

        Scanner sc = new Scanner(System.in);

        String message = "Hello, Welcome to Hallur&Mo car rent, please pick a car that you would like to rent:" +
                "\n1-Mercdes CLA200 Benz," +
                " \n2-BMW i530 Benz!," +
                "\n3-Toyota Aygo 2010-benz, " +
                "\n3-mercedes E200 2015-Ben";
        createQueue(message);
        System.out.println(" [x] Sent '" + message + "'");
    }

    public static void createQueue(String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        try
        {
            channel.queueDeclare(QUEUE_Car, false, false, false, null);
            channel.basicPublish("", QUEUE_Car, null, message.getBytes("UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        channel.queueDeclare(QUEUE_Car, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // Get notified, if a message for this receiver arrives
        DeliverCallback deliverCallback = (consumerTag, delivery) ->
        {
            String message2 = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message2 + "'");
            if(message2.equals("1")){
                String msg = "you order mercdes";
                channel.basicPublish("", QUEUE_Car, null, msg.getBytes("UTF-8"));
            }
        };
        channel.basicConsume(QUEUE_Car, true, deliverCallback, consumerTag -> {});
    }

     */


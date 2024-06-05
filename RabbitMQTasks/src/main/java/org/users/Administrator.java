package org.users;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static org.tools.Constants.*;

public class Administrator {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private String administratorName;

    public Administrator(String administratorName) {
        this.administratorName = administratorName;
    }


    public void start(){

        try {
            this.factory = new ConnectionFactory();
            this.factory.setHost(LOCALHOST);
            this.connection = factory.newConnection();

            this.channel = connection.createChannel();
            this.channel.exchangeDeclare(EXCHANGE_LOG_NAME,BuiltinExchangeType.FANOUT);
            this.channel.exchangeDeclare(EXCHANGE_INFO_NAME,BuiltinExchangeType.FANOUT);
            this.channel.basicQos(1);

            this.channel.queueDeclare(this.administratorName, true, false, false, null);
            this.channel.queueBind(this.administratorName, EXCHANGE_LOG_NAME, this.administratorName);
            this.channel.basicConsume(this.administratorName, false, this.createConsumer());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            while (true) {

               System.out.println("Enter info message:");
               String message = br.readLine();
               if("exit".equals(message)){
                   break;
               }
               else if(!message.isBlank()){
                   this.channel.basicPublish(EXCHANGE_INFO_NAME,"", null, message.getBytes(StandardCharsets.UTF_8));
               }
               else{
                   System.out.println("Incorrect message");
               }
            }
            this.channel.close();
            this.connection.close();

        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }

    }

    private Consumer createConsumer(){
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);

                channel.basicAck(envelope.getDeliveryTag(), false);
                System.out.println(message);
            }
        };
    }


    public static void main(String[] args){
        if(args.length>=1){
            String administratorName = args[0];
            if(administratorName.length()>1){
                Administrator administrator = new Administrator(administratorName);
                administrator.start();
            }
            else{
                System.out.println("Invalid name");
            }
        }
        else{
            System.out.println("Invalid number of arguments");
        }
    }
}

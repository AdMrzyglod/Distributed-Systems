package org.users;

import com.rabbitmq.client.*;
import org.tools.RandomInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.tools.Constants.*;

public class Technician {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private List<String> types;
    private String technicianName;

    public Technician(String technicianName, List<String> types) {
        this.technicianName = technicianName;
        this.types = types;
    }


    public void start(){

        try {
            this.factory = new ConnectionFactory();
            this.factory.setHost(LOCALHOST);
            this.connection = factory.newConnection();

            this.channel = connection.createChannel();
            this.channel.exchangeDeclare(EXCHANGE_EXAMINATION_REQUEST_NAME, BuiltinExchangeType.DIRECT);
            this.channel.exchangeDeclare(EXCHANGE_EXAMINATION_RESULT_NAME,BuiltinExchangeType.DIRECT);
            this.channel.exchangeDeclare(EXCHANGE_LOG_NAME,BuiltinExchangeType.FANOUT);
            this.channel.exchangeDeclare(EXCHANGE_INFO_NAME,BuiltinExchangeType.FANOUT);
            this.channel.basicQos(1);
            Consumer consumer = this.createConsumer();

            for(String type: types){
                this.channel.queueDeclare(type+QUEUE_NAME, true, false, false, null);
                this.channel.queueBind(type+QUEUE_NAME, EXCHANGE_EXAMINATION_REQUEST_NAME, type);
                this.channel.basicConsume(type+QUEUE_NAME, false, consumer);
            }

            this.channel.queueDeclare(this.technicianName, true, false, false, null);
            this.channel.queueBind(this.technicianName, EXCHANGE_INFO_NAME, "");
            this.channel.basicConsume(this.technicianName, false, this.createInfoConsumer());


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.println("Enter data:");
                String message = br.readLine();

                if("exit".equals(message)){
                    break;
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

                String[] data = message.split("_",3);
                String type = data[0];
                String lastname = data[1];
                String doctorName = data[2];
                String response = lastname+"_"+type+"_"+"done";
                String logResponse = "TECHNICIAN: "+technicianName+"_"+lastname+"_"+type+"_"+"done";

                try {
                    Thread.sleep(RandomInt.getRandomInt(2000,5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(message);
                channel.basicAck(envelope.getDeliveryTag(), false);
                channel.basicPublish(EXCHANGE_EXAMINATION_RESULT_NAME, doctorName, null, response.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(EXCHANGE_LOG_NAME, "", null, logResponse.getBytes(StandardCharsets.UTF_8));
            }
        };
    }

    private Consumer createInfoConsumer(){
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
        if(args.length>=3){
            String technicianName = args[0];
            List<String> types = List.of(args[1],args[2]);
            if(!TYPES.containsAll(types)){
                System.out.println("Incorrect types");
                return;
            }
            Technician technician = new Technician(technicianName,types);
            technician.start();
        }
        else{
            System.out.println("Invalid number of arguments");
        }
    }

}

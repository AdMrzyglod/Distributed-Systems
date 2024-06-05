package org.users;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static org.tools.Constants.*;

public class Doctor {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private String doctorName;

    public Doctor(String doctorName) {
        this.doctorName = doctorName;
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

            this.channel.queueDeclare(this.doctorName, true, false, false, null);
            Consumer consumer = this.createConsumer();

            this.channel.queueBind(this.doctorName, EXCHANGE_EXAMINATION_RESULT_NAME, this.doctorName);
            this.channel.queueBind(this.doctorName, EXCHANGE_INFO_NAME, "");
            this.channel.basicConsume(this.doctorName, false, consumer);


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.println("Enter data:");
                String message = br.readLine();

                if("exit".equals(message)){
                    break;
                }
                else if(message.contains("_")){
                    String[] data = message.split("_",2);
                    if(data.length!=2 || data[0].length()==0 || data[1].length()==0 || !TYPES.contains(data[0])){
                        System.out.println("Incorrect message");
                    }
                    else{
                        String type = data[0];
                        String lastname = data[1];
                        String msg = type+"_"+lastname+"_"+this.doctorName;
                        String logMsg = "DOCTOR: "+this.doctorName+"_"+type+"_"+lastname;
                        this.channel.basicPublish(EXCHANGE_EXAMINATION_REQUEST_NAME, type, null, msg.getBytes(StandardCharsets.UTF_8));
                        this.channel.basicPublish(EXCHANGE_LOG_NAME, "", null, logMsg.getBytes(StandardCharsets.UTF_8));
                        System.out.println("Sent: " + message);
                    }
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
            String doctorName = args[0];
            if(doctorName.length()>1){
                Doctor doctor = new Doctor(doctorName);
                doctor.start();
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

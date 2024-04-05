package org.client;

import org.tools.AsciiArtStorage;
import org.tools.DatagramTools;
import org.tools.Message;
import org.tools.MessageTools;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientSendManager implements Runnable {

    private ChatClient chatClient;
    private DatagramTools datagramTools;

    private Scanner scanner = new Scanner(System.in);

    public ClientSendManager(ChatClient chatClient) {
        this.chatClient = chatClient;
        this.datagramTools = new DatagramTools();
    }

    @Override
    public void run() {

        try{

            while(!this.chatClient.isEndState()){

                if(this.scanner.hasNextLine()){
                    System.out.print(">>> ");
                    String message = this.scanner.nextLine();

                    if(!message.isBlank()){
                        this.parseArguments(message);
                    }
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void parseArguments(String message) throws IOException {

        List<String> arguments = Arrays.stream(message.trim().split(" ",2)).toList();
        String option = arguments.get(0);

        if(option.equals("EXIT")){
            this.chatClient.getOut().println(MessageTools.generateStringMessage(new Message("EXIT"," ")));
            this.chatClient.closeConnection();
        }
        else if(option.equals("U")){
            DatagramPacket datagramPacket = datagramTools.cleanBufferAndBuildDatagram(
                    MessageTools.generateStringMessage(new Message("UDP_MSG",">>>["+this.chatClient.getNickname()+"]: \n"+AsciiArtStorage.art)),
                    InetAddress.getByName(this.chatClient.HOST_NAME),this.chatClient.PORT_NUMBER);

            this.chatClient.getDatagramSocket().send(datagramPacket);
        }
        else if(option.equals("M")){
            DatagramPacket datagramPacket = datagramTools.cleanBufferAndBuildDatagram(
                    MessageTools.generateStringMessage(new Message("MTC_MSG",">>>["+this.chatClient.getNickname()+"]: \n"+AsciiArtStorage.art)),
                    InetAddress.getByName(this.chatClient.MULTICAST_ADDRESS),this.chatClient.MULTICAST_PORT);

            this.chatClient.getMulticastSocket().send(datagramPacket);
        }
        else{
            this.chatClient.getOut().println(MessageTools.generateStringMessage(new Message("TCP_MSG",">>>["+this.chatClient.getNickname()+"]: "+message)));
        }
    }

}

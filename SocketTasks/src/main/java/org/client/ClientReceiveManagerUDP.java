package org.client;

import org.tools.DatagramTools;
import org.tools.Message;
import org.tools.MessageTools;

import java.net.DatagramPacket;

public class ClientReceiveManagerUDP implements Runnable{
    private ChatClient chatClient;
    private DatagramTools datagramTools;

    public ClientReceiveManagerUDP(ChatClient chatClient) {
        this.chatClient = chatClient;
        this.datagramTools = new DatagramTools();
    }

    @Override
    public void run() {

        try{

            while(!this.chatClient.isEndState()){

                DatagramPacket datagramPacket = this.datagramTools.cleanBufferAndBuildDatagram();

                this.chatClient.getDatagramSocket().receive(datagramPacket);

                String msg = datagramTools.getMessageFromDatagram(datagramPacket);
                Message message = MessageTools.parseStringMessage(msg);

                if(message.getType().equals("UDP_MSG")){
                    System.out.println(message.getContent());
                }

            }
        } catch (Exception e){
            if(!this.chatClient.isEndState()){
                e.printStackTrace();
            }
        }
    }

}

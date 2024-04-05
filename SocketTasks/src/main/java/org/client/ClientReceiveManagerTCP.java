package org.client;

import org.tools.Message;
import org.tools.MessageTools;

import java.io.IOException;
import java.net.SocketException;

public class ClientReceiveManagerTCP implements Runnable{

    private ChatClient chatClient;

    public ClientReceiveManagerTCP(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {

        System.out.print(">>> ");
        try{

            while(!this.chatClient.isEndState()){

                String msg = this.chatClient.getIn().readLine();
                Message message = MessageTools.parseStringMessage(msg);

                if(message.getType().equals("SERVER_EXIT")) {
                    System.out.println(">>> SERVER END CONNECTION");
                    this.chatClient.closeConnection();
                    break;
                }
                else if(message.getType().equals("TCP_MSG")){
                    System.out.println();
                    System.out.println(message.getContent());
                    System.out.print(">>> ");
                }

            }
        }catch (SocketException e){

        } catch (IOException e){
            if(!this.chatClient.isEndState()){
                e.printStackTrace();
            }
        }
        finally {
            this.chatClient.closeConnection();
        }

    }
}

package org.server;

import org.tools.Message;
import org.tools.MessageTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class UserTCPManager implements Runnable{

    private ChatServer chatServer;
    private Socket clientSocket;
    private String nickname;
    private PrintWriter out;
    private BufferedReader in;

    private boolean endState;


    public UserTCPManager(ChatServer chatServer, Socket clientSocket) throws IOException {

        this.endState = false;
        this.chatServer = chatServer;
        this.clientSocket = clientSocket;
        this.out = new PrintWriter(this.clientSocket.getOutputStream(),true);
        this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

        this.startConnection();
    }


    @Override
    public void run() {

        try{

            while(!this.endState){

                String message = this.in.readLine();
                Message msg = MessageTools.parseStringMessage(message);

                if(msg.getType().equals("EXIT")){
                    this.endState=true;
                }
                else if(msg.getType().equals("TCP_MSG")){
                    this.broadcastMessageTCP(this.nickname,message);
                }
            }
            this.clientSocket.close();

        } catch (SocketException e){

        } catch (IOException e){
            if(!this.endState){
                e.printStackTrace();
            }
        } finally {
            this.chatServer.getServerManager().removeUserFromChat(this,Thread.currentThread());
            System.out.println(this.nickname+" DISCONNECTED");
            System.out.print(">>> ");
        }

    }

    public void startConnection(){

        try {
            Message initMsg = MessageTools.parseStringMessage(this.in.readLine());

            if(initMsg.getType().equals("INIT")){
                this.nickname = initMsg.getContent();

                if(this.chatServer.getServerManager().isUserExist(this.nickname)){
                    this.out.println(MessageTools.generateStringMessage(new Message("REJECT"," ")));
                    this.endState=true;
                    return;
                }

                this.out.println(MessageTools.generateStringMessage(new Message("ACCEPT"," ")));
                System.out.println(this.nickname+" CONNECTED");
                System.out.print(">>>");
            }
            else{
                this.out.println(MessageTools.generateStringMessage(new Message("REJECT"," ")));
                this.endState=true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessageTCP(String nickname, String message){
        try {
            this.chatServer.getServerManager().getSemaphore().acquire();

            for(UserTCPManager user: this.chatServer.getServerManager().getUserManagerList()){

                if(!nickname.equals(user.getNickname())){
                    user.getOut().println(message);
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            this.chatServer.getServerManager().getSemaphore().release();
        }
    }

    public void endConnection() throws IOException {

        this.out.println(MessageTools.generateStringMessage(new Message("SERVER_EXIT"," ")));
        this.endState=true;
        this.clientSocket.close();
    }

    public String getNickname() {
        return nickname;
    }

    public PrintWriter getOut() {
        return out;
    }

    public int getPortNumber(){
        return this.clientSocket.getPort();
    }
}

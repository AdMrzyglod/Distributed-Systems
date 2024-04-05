package org.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {

    public final int PORT_NUMBER = 12345;
    public final String HOST_NAME = "localhost";
    private ServerSocket serverSocket;
    private DatagramSocket datagramSocket;
    private boolean endState;
    private ServerManager serverManager;


    public static void main(String[] args) throws IOException {

        ChatServer chatServer = new ChatServer();
        chatServer.run();
    }

    public ChatServer() {
        this.init();
    }

    private void init(){
        System.out.println(">>> MAIN SERVER");
        System.out.println(">>> START");

        this.serverSocket = null;
        this.endState = false;
        this.serverManager = new ServerManager();
        this.serverManager.setUserUDPManager(new UserUDPManager(this));

        try {
            this.serverSocket =  new ServerSocket(this.PORT_NUMBER);
            this.datagramSocket = new DatagramSocket(this.PORT_NUMBER);

            Thread line = new Thread(new ServerLine(this));
            Thread udpThread = new Thread(this.serverManager.getUserUDPManager());

            line.start();
            udpThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.closeServer()));
    }

    private void run(){

        try {
            while(!this.isEndState()){

                Socket clientSocket = this.serverSocket.accept();

                UserTCPManager userTCPManager = new UserTCPManager(this,clientSocket);
                Thread userThread = new Thread(userTCPManager);
                this.serverManager.addUserToChat(userTCPManager,userThread);
                userThread.start();

            }
        } catch (IOException e) {

        }
    }

    protected void closeServer(){
        if(this.isEndState()){
            return;
        }
        try {
            this.endState=true;
            this.serverManager.closeConnectedClients();
            this.serverSocket.close();
            this.datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(">>> SERVER CLOSED");
        }
    }

    public boolean isEndState() {
        return endState;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}

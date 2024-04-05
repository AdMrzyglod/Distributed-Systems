package org.client;


import org.tools.Message;
import org.tools.MessageTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ChatClient {

    public final int PORT_NUMBER = 12345;
    public final String HOST_NAME = "localhost";
    public final String MULTICAST_ADDRESS = "238.10.10.10";
    public final int MULTICAST_PORT = 45555;
    private String nickname;
    private Socket socket;
    private DatagramSocket datagramSocket;
    private MulticastSocket multicastSocket;
    private PrintWriter out;
    private BufferedReader in;
    private ClientReceiveManagerTCP receiveTCP;
    private ClientReceiveManagerUDP receiveUDP;
    private  ClientReceiveManagerMulticast receiveMulticast;
    private ClientSendManager send;
    private boolean endState;


    public static void main(String[] args) throws IOException {

        ChatClient chatClient = new ChatClient(args[0]);
        chatClient.run();
    }

    public ChatClient(String nickname) throws IOException {
        this.init(nickname);
    }

    private void init(String nickname) throws IOException {

        System.out.println(">>> CLIENT");

        this.endState = false;
        this.nickname=nickname;

        try {
            this.socket = new Socket(this.HOST_NAME,this.PORT_NUMBER);
            this.datagramSocket = new DatagramSocket(this.socket.getLocalPort());
            this.multicastSocket = new MulticastSocket(this.MULTICAST_PORT);

            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.multicastSocket.joinGroup(InetAddress.getByName(this.MULTICAST_ADDRESS));

            this.send = new ClientSendManager(this);
            this.receiveTCP = new ClientReceiveManagerTCP(this);
            this.receiveUDP = new ClientReceiveManagerUDP(this);
            this.receiveMulticast = new ClientReceiveManagerMulticast(this);

        } catch(Exception e){
            e.printStackTrace();
        }

        this.out.println(MessageTools.generateStringMessage(new Message("INIT",this.getNickname())));

        if(MessageTools.parseStringMessage(this.in.readLine()).getType().equals("ACCEPT")){
            System.out.println(">>> CONNECTION SUCCESSFULLY");
        }
        else{
            System.out.println(">>> CONNECTION REJECTED");
            this.closeConnection();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.closeConnection()));
    }

    private void run(){

        if(this.endState){
            return;
        }

        List<Thread> threadList = Arrays.asList(new Thread(this.send),new Thread(this.receiveTCP),new Thread(this.receiveUDP),new Thread(this.receiveMulticast));
        threadList.forEach((thread -> thread.start()));

    }

    public void closeConnection(){
        if(this.isEndState()){
            return;
        }
        try {
            this.endState=true;
            this.socket.close();
            this.datagramSocket.close();
            this.multicastSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(">>> CONNECTION CLOSED");
            System.exit(0);
        }
    }


    public String getNickname() {
        return nickname;
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public boolean isEndState() {
        return endState;
    }

    public BufferedReader getIn() {
        return in;
    }

    public MulticastSocket getMulticastSocket() {
        return multicastSocket;
    }

    public void closeEndState(){
        this.endState=true;
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}

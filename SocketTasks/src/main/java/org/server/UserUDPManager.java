package org.server;

import org.tools.DatagramTools;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class UserUDPManager implements Runnable{

    private ChatServer chatServer;
    private DatagramTools datagramTools;
    public UserUDPManager(ChatServer chatServer) {
        this.chatServer = chatServer;
        this.datagramTools = new DatagramTools();
    }

    @Override
    public void run() {

        try {

            while(!this.chatServer.isEndState()) {

                DatagramPacket datagramPacket = this.datagramTools.cleanBufferAndBuildDatagram();
                this.chatServer.getDatagramSocket().receive(datagramPacket);
                String msg = this.datagramTools.getMessageFromDatagram(datagramPacket);

                this.broadcastMessageUDP(datagramPacket.getPort(), msg);

            }

        } catch (IOException e) {
            if(!this.chatServer.isEndState()){
                e.printStackTrace();
            }
        }
    }


    public void broadcastMessageUDP(int portNumber, String message){
        try {
            this.chatServer.getServerManager().getSemaphore().acquire();

            for(UserTCPManager user: this.chatServer.getServerManager().getUserManagerList()){

                if(portNumber!=user.getPortNumber()){
                    DatagramPacket datagramPacket = this.datagramTools.cleanBufferAndBuildDatagram(message,
                            InetAddress.getByName(this.chatServer.HOST_NAME), user.getPortNumber());

                    this.chatServer.getDatagramSocket().send(datagramPacket);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.chatServer.getServerManager().getSemaphore().release();
        }

    }
}

package org.tools;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DatagramTools {

    private final int BUFFER_SIZE = 1024;
    private byte[] buffer;

    public DatagramTools() {
        this.buffer = new byte[BUFFER_SIZE];
    }

    public DatagramPacket cleanBufferAndBuildDatagram(){
        Arrays.fill(buffer, (byte)0);
        return new DatagramPacket(buffer, buffer.length);
    }

    public DatagramPacket cleanBufferAndBuildDatagram(String data,InetAddress inetAddress,int portNumber){
        byte[] array = data.getBytes();
        return new DatagramPacket(array, array.length,inetAddress,portNumber);
    }

    public String getMessageFromDatagram(DatagramPacket datagramPacket){
        return new String(datagramPacket.getData(), StandardCharsets.UTF_8);
    }
}

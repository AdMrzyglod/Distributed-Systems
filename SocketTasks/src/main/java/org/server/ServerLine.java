package org.server;

import java.util.Scanner;

public class ServerLine implements Runnable{

    private ChatServer chatServer;
    private Scanner scanner = new Scanner(System.in);

    public ServerLine(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    @Override
    public void run() {

        System.out.print(">>>");

        while(!this.chatServer.isEndState()){

            if(this.scanner.hasNextLine()){
                System.out.print(">>>");
                String command = this.scanner.nextLine();

                if(command.trim().equals("EXIT")){
                    this.chatServer.closeServer();
                }
            }

        }
    }
}

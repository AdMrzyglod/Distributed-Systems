package org.ice;


import org.ice.server.ServerICE;

public class Main {

    public static void main(String[] args){
        ServerICE serverICE = new ServerICE();
        if(args.length>=1){
            int number = Integer.parseInt(args[0]);
            if(number>50 || number<2){
                System.out.println("Niepoprawny numer serwera");
            }
            serverICE.startServer(args,number);
        }
        else{
            System.out.println("Podaj numer serwera");
        }
    }
}

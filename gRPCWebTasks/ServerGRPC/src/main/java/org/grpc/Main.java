package org.grpc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final ServerGRPC server = new ServerGRPC();
        server.start();
        server.blockUntilShutdown();
    }
}
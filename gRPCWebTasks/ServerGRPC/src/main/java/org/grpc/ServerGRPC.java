package org.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ServerGRPC {

    private static final Logger logger = Logger.getLogger(ServerGRPC.class.getName());
    private int port = 9090;
    private Server server;

    public void start() throws IOException
    {

        server = ServerBuilder.forPort(port).executor((Executors.newFixedThreadPool(16)))
                .addService(new SurveyServiceI())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down gRPC server...");
                ServerGRPC.this.stop();
                System.err.println("Server shut down.");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }


    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}

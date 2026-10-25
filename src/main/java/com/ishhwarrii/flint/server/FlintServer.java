package com.ishhwarrii.flint.server;

import com.ishhwarrii.flint.command.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FlintServer {

    private final int port;
    private final RequestHandler handler;

    public FlintServer(int port, RequestHandler handler) {
        this.port = port;
        this.handler = handler;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Flint listening on port " + port);
            while (true) {
                try (Socket client = serverSocket.accept()) {
                    System.out.println("Client connected: " + client.getRemoteSocketAddress());
                    serve(client);
                    System.out.println("Client disconnected");
                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }
        }
    }

    private void serve(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);

        String line;
        while ((line = in.readLine()) != null) {
            String response = handler.handle(line);
            out.println(response == null ? "(nil)" : response);
        }
    }
}

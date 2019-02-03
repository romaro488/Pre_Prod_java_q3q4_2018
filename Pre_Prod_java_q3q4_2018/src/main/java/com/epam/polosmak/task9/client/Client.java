package com.epam.polosmak.task9.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.epam.polosmak.task4.constant.Constants.PORT_TCP;

public class Client {
    private Integer port;
    private String host;
    private static final String IP = "localhost";
    public Client(Integer port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() {
        try (Socket server = new Socket(host, port);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(server.getOutputStream(), true)) {

            System.out.printf("Client: %s (Enter 'stop' to exit)\n%n", server.getPort());
            System.out.print("Please enter the command or ('exit' to exit from the store)\n" +
                    "1)get count\n" +
                    "2)get item = (item name)\n ");
            String msg = null;
            while (!"stop".equals(msg)) {
                msg = console.readLine();
                out.println(msg);
                System.out.println("Response: " + serverIn.readLine());
            }
        } catch (IOException ex) {
            System.out.println("Connection problem.");
        }
    }

    public static void main(String[] args) {
        Client client = new Client(PORT_TCP, IP);
        client.start();
    }
}

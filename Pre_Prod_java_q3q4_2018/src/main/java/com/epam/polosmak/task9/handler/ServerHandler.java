package com.epam.polosmak.task9.handler;

import com.epam.polosmak.task9.factory.thread.ServerThread;
import com.epam.polosmak.task9.webCommand.WebCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler extends Thread {
    private WebCommand webCommand;
    private ServerSocket serverSocket;
    private int port;

    public ServerHandler(int port, WebCommand webCommand) {
        this.port = port;
        this.webCommand = webCommand;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servers has started");
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    new ServerThread(socket, webCommand).start();
                } catch (IOException e) {
                    socket.close();
                }
            }
        } catch (IOException e) {
            System.out.println("can't create ServerSocket");
        }
    }
}

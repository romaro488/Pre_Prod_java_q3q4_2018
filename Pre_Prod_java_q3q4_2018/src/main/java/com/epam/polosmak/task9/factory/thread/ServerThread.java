package com.epam.polosmak.task9.factory.thread;

import com.epam.polosmak.task9.webCommand.WebCommand;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread {

    private WebCommand webCommand;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter printWriter;

    public ServerThread(Socket socket, WebCommand webCommand) throws IOException {
        this.socket = socket;
        this.webCommand = webCommand;
        reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    public static String header(String response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1.200 OK\r\n").append("ServerHandler: Personal/").append(new Date().toString())
                .append("\r\n")
                .append("Content-Type: application/json\r\n")
                .append("Content-Length: ")
                .append(response.getBytes().length)
                .append("\r\n")
                .append("Connection: close\r\n\r\n")
                .append(response);
        return stringBuilder.toString();
    }

    @Override
    public void run() {
        try {
            String request = reader.readLine();
            printWriter.write(header(webCommand.getCommand(request)));
            printWriter.println(header(webCommand.getCommand(request)));
        } catch (IOException e) {
            System.out.println("Can't read input command");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("socket not closed");
            }
        }
    }
}
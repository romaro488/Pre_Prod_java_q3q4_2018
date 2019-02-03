
package com.epam.polosmak.task9.factory.thread;

import com.epam.polosmak.task9.webCommand.WebCommand;

import java.io.*;
import java.net.Socket;

public class TcpServerThread extends Thread {

    private WebCommand webCommand;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter printWriter;

    public TcpServerThread(Socket socket, WebCommand webCommand) throws IOException {
        this.socket = socket;
        this.webCommand = webCommand;
        reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
    }

    public void run() {
        try {
            String request;
            while ((request = this.reader.readLine()) != null) {
                if (request.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Client: " + request);
                printWriter.println(webCommand.getCommand(request));
            }
        } catch (IOException e) {
            System.out.println("Can't read input data");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}


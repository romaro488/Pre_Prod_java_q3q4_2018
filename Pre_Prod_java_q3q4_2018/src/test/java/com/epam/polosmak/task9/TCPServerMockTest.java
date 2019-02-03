package com.epam.polosmak.task9;

import com.epam.polosmak.task9.factory.thread.TcpServerThread;
import com.epam.polosmak.task9.webCommand.WebCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static com.epam.polosmak.task4.constant.Constants.PORT_TCP;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TCPServerMockTest {

    private static final String COUNT_OF_PRODUCTS_IN_SHOP = "5";
    private static final String GET_COUNT = "get count";
    private static final String GET_ITEM_PUMA = "get item = puma";
    private static final String RESPONSE_ON_COMMAND_GET_ITEM = "puma | 353.0";
    private static final String WRONG_COMMAND = "wrong command";
    private static final String LOCALHOST = "localhost";
    private static final int PORT = 3000;

    private Thread thread;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ServerSocket serverSocket;

    @Mock
    private WebCommand command;

    @Before
    public void init() {
        command = Mockito.mock(WebCommand.class);

        thread = new Thread() {
            @Override
            public void run() {

                try {
                    serverSocket = new ServerSocket(PORT_TCP);
                    Socket socket = serverSocket.accept();
                    new TcpServerThread(socket, command).start();
                } catch (IOException e) {
                    System.out.println("Socket close");
                }
            }
        };
    }

    @After
    public void destroy() throws IOException {
        socket.close();
        serverSocket.close();
        in.close();
        out.close();
        thread.interrupt();
    }

    @Test
    public void shouldCheckGetItemCommandTCPServer() throws IOException {

        when(command.getCommand(GET_ITEM_PUMA)).thenReturn(RESPONSE_ON_COMMAND_GET_ITEM);

        thread.start();

        socket = new Socket(LOCALHOST, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println(GET_ITEM_PUMA);
        command.getCommand(GET_ITEM_PUMA);
        verify(command).getCommand(GET_ITEM_PUMA);
    }

    @Test
    public void shouldCheckGetCountCommandTCPServer() throws IOException {

        when(command.getCommand(GET_COUNT)).thenReturn(COUNT_OF_PRODUCTS_IN_SHOP);

        thread.start();

        socket = new Socket(LOCALHOST, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println(GET_COUNT);

        command.getCommand(GET_COUNT);
        verify(command).getCommand(GET_COUNT);
    }


    @Test
    public void shouldCheckWrongCommandCommandTCPServer() throws IOException {

        when(command.getCommand(WRONG_COMMAND)).thenReturn(WRONG_COMMAND);

        thread.start();

        socket = new Socket(LOCALHOST, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println(WRONG_COMMAND);
        verify(command).getCommand(WRONG_COMMAND);
    }
}





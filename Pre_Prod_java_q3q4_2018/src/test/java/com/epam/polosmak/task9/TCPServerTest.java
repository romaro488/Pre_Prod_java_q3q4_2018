package com.epam.polosmak.task9;

import com.epam.polosmak.task4.command.ConsoleManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TCPServerTest {

    private static final int COUNT_OF_PRODUCTS_IN_SHOP = 5;
    private static final int WRONG_COUNT_OF_PRODUCTS_IN_SHOP = 6;
    private static final String GET_COUNT = "get count";
    private static final String GET_ITEM_COMMAND = "get item";
    private static final String GET_ITEM_COMMAND_PUMA = GET_ITEM_COMMAND + " = puma";
    private static final String SOME_WRONG_COMMAND = "some wrong command";
    private static final String WRONG_COMMAND = "wrong command";
    private static final String PRODUCT_NAME = "puma | 353.0";
    private static final String WRONG_INPUT_COMMAND = "wrong input";
    private static final String HOST = "localhost";
    private static final int PORT_TCP = 3000;

    private Thread thread;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Before
    public void init() throws IOException {
        thread = new Thread(() -> {
            ConsoleManager consoleCommand = new ConsoleManager();
            try {
                consoleCommand.command();
            } catch (IOException e) {
                System.out.println(WRONG_INPUT_COMMAND);
            }
        });
        thread.start();
        socket = new Socket(HOST, PORT_TCP);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @After
    public void destroy() throws IOException {
        socket.close();
        reader.close();
        writer.close();
        thread.interrupt();
    }

    @Test
    public void shouldCheckCommandGetCommandTCPServer() throws IOException {
        writer.println(GET_COUNT);
        String response = reader.readLine();
        assertEquals(COUNT_OF_PRODUCTS_IN_SHOP, Integer.parseInt(response));
    }

    @Test
    public void shouldCheckCommandGetCommandTCPServerWrongExpectedValue() throws Exception {
        writer.println(GET_COUNT);
        String response = reader.readLine();
        assertNotEquals(WRONG_COUNT_OF_PRODUCTS_IN_SHOP, Integer.parseInt(response));
    }

    @Test
    public void shouldCheckCommandGetItemTCPServer() throws Exception {
        writer.println(GET_ITEM_COMMAND + GET_ITEM_COMMAND_PUMA);
        String response = reader.readLine();
        assertEquals(PRODUCT_NAME, response);
    }

    @Test
    public void shouldCheckWrongCommandTCPServer() throws Exception {
        writer.println(SOME_WRONG_COMMAND);
        String response = reader.readLine();
        assertEquals(WRONG_COMMAND, response);
    }
}

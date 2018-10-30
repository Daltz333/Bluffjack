package sockets;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;

public class ClientHandler {
    private Thread receiveThread;
    private Thread sendThread;
    private boolean connected;
    private Socket clientSocket;
    private String clientName;
    private String sendData;
    private String data;

    private Controller controller = new Controller();

    public ClientHandler(String name, Socket s) {
        clientSocket = s;
        clientName = name;

        receiveThread = new Thread(this::receive);
        sendThread = new Thread(this::send);

        connected = clientSocket.isConnected();

        receiveThread.start();
        sendThread.start();

    }

    private void receive() {
        System.out.println("Processing Data");
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            connected = false;
        }

        System.out.println("Reader Connected? " + connected);
        while (connected) {
            try {
                //check if gotten value from stream
                if (in.ready()) {
                    controller.handleReceivedPacket(clientName, in);

                } else {


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void send() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream());

        } catch (IOException e) {
            connected = false;

        }
        while (connected) {
            String toSend = sendData;
            if (toSend != null) {
                out.println(toSend);

            }
        }
    }

    public void send(String data) {
        sendData = data;

    }

    public void close() {
        connected = false;
    }

}


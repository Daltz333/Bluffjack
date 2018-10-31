package sockets;

import constants.GeneralConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client {
    private boolean running;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private boolean connected = false;
    private boolean connectToClient = true;
    private ConcurrentLinkedQueue<String> receiveQueue = new ConcurrentLinkedQueue<>();

    public synchronized void connectToHost(String hostName) {

        Thread clientConnect = new Thread(() -> {
            BufferedReader readFromServer = null;
            while (connectToClient) {
                try {
                    socket = new Socket(hostName, GeneralConstants.applicationPort);
                    readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    connected = true;
                    System.out.println("Connected to host: " + connected);

                } catch (IOException e) {
                    System.out.println("Does host exist?");
                    connectToClient = false;

                }

                while (connected) {
                    if (readFromServer == null) {
                        System.out.println("Connection to server was not established");
                        break;

                    }

                    try {
                        if (readFromServer.ready()) {
                            receiveQueue.add(readFromServer.readLine());

                            if (!readFromServer.ready()) {
                                if (readFromServer.read() == -1) {
                                    System.out.println("Host disconnected");
                                    connected = false;

                                }

                            }

                        }

                    } catch (IOException e) {
                        System.out.println("Unable to read from server");

                    }

                }

            }

            try {
                socket.close();

                if (readFromServer != null) {
                    readFromServer.close();
                }

            } catch (IOException e) {
                System.out.println("Unable to properly stop the client");

            }

        });

        if (!clientConnect.isAlive()) {
            clientConnect.setDaemon(true);
            clientConnect.start();

        }

    }

    public synchronized void stopClient() {
        connectToClient = false;
        connected = false;

    }

    public synchronized String returnSocketData() {
        return receiveQueue.poll();

    }

    public boolean sendSocketData(String data) {
        if (socket == null) {
            return false;

        }

        try {
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            return false;

        }

        out.println(data);
        return true;

    }

    public boolean returnClientConnected () {

        return connected;
    }


}

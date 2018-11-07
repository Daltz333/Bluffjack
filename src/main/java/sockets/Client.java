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
    private boolean connectingToClient = false;
    private boolean forceStop = false;

    public synchronized void connectToHost(String hostName) {
        Thread clientConnect = new Thread(() -> {
            BufferedReader readFromServer = null;
            while (connectToClient) {
                try {
                    connectingToClient = true;
                    socket = new Socket(hostName, GeneralConstants.applicationPort);
                    readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    connected = true;
                    System.out.println("Connected to host: " + socket.getInetAddress());

                    if (forceStop == true) {
                        forceStop = false;
                        break;
                    }

                } catch (IOException e) {
                    System.out.println("Does host exist?");
                    connected = false;
                    break;

                }

                try {
                    out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("[Username] " + GeneralConstants.userName);

                } catch (IOException e) {
                    e.printStackTrace();

                }

                while (connected) {
                    if (readFromServer == null) {
                        System.out.println("Connection to server was not established");
                        break;

                    }

                    try {
                        if (readFromServer.ready()) {
                            String data = readFromServer.readLine();
                            System.out.println("Client Received: " + data);
                            receiveQueue.add(data);

                            if (!readFromServer.ready()) {
                                System.out.println("No more data detected");

                            }

                        } else {
                            String data = readFromServer.readLine();

                            if (data == null) {
                                connected = false;

                            } else {
                                System.out.println("Client Received: " + data);
                                receiveQueue.add(data);

                            }
                        }

                    } catch (IOException e) {
                        System.out.println("Unable to read from server");
                        connected = false;

                    }

                }

            }

            try {
                if (!(socket == null)) {
                    socket.close();
                }

                if (readFromServer != null) {
                    readFromServer.close();
                }

            } catch (IOException e) {
                System.out.println("Unable to properly stop the client");

            }

        });

        System.out.println(Thread.activeCount());

        if (connectingToClient == false) {
            System.out.println("Starting host");
            clientConnect.setDaemon(true);
            clientConnect.start();

        } else {
            stopClient();
            System.out.println("Stopping Client");
            connectingToClient = false;

        }

    }

    //check if the client is connected
    public synchronized boolean clientActive() {

        return connected;
    }

    //stop our client
    public synchronized void stopClient() {
        forceStop = true;
        connected = false;

    }

    public synchronized String returnSocketData() {
        return receiveQueue.poll();

    }

    public synchronized String peekData() {
        return receiveQueue.peek();
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

}

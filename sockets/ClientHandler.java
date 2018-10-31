package sockets;

import constants.GeneralConstants;

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
	private Controller controller;
	
	public ClientHandler(String name, Socket s, Controller c) {
		clientSocket = s;
		clientName = name;
		controller = c;

		receiveThread = new Thread(this::receive);

		connected = clientSocket.isConnected();

	}


	public void receive() {
		System.out.println("Processing Data");
		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		} catch (IOException e) {
			connected = false;
		}

		System.out.println("Reader Connected? " + connected);
		while (connected) {

		    if (in == null) {
		        connected = false;
		        break;

            }

			try {
				//check if gotten value from stream, add values
				while (in.ready()) {
                    controller.handleReceivedPacket(clientName, in);

                }
                Thread.sleep(GeneralConstants.hostTimeout);
				Thread.yield();

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean sendData(String data) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(data);
            return true;

        } catch (IOException e) {
            System.out.println("Unable to create output stream to client");
            return false;

        }
    }

	public void close() {
		connected = false;

	}

}


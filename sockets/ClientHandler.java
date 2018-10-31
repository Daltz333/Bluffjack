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
	private Controller controller;
	
	public ClientHandler(String name, Socket s, Controller c) {
		clientSocket = s;
		clientName = name;
		controller = c;

		receiveThread = new Thread(this::receive);
		sendThread = new Thread(this::send);

		connected = clientSocket.isConnected();

		//receiveThread.start();
		//sendThread.start();

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
			try {
				// check if gotten value from stream
				if (in.ready()) {
					controller.handleReceivedPacket(clientName, in);

				} else {
					Thread.sleep(1000);
				}
				Thread.yield();
			} catch (IOException | InterruptedException e) {
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

	public void setController(Controller c) {
		controller =c;
	}

}


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

public class Client {
	private boolean running;
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;

	public synchronized void connectToHost(String hostName) {
		try {
			socket = new Socket(hostName, GeneralConstants.applicationPort);

		} catch (IOException e) {
			System.out.println("Does host exist?");

		}

	}

	public synchronized void stopClient() {
		try {
			socket.close();

		} catch (IOException e) {
			System.out.println("Unable to close client socket");

		}

	}

	public String returnSocketData() throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String data = in.readLine();
		in.close();
		return data;

	}

	public void sendSocketData(String data) throws IOException {
		out = new PrintWriter(socket.getOutputStream(), true);
		out.println(data);

	}

	public boolean returnClientConnected() {

		return socket.isConnected();
	}

}

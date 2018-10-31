package sockets;

import constants.GeneralConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Controller {

	ConcurrentLinkedQueue<String> stringQueue = new ConcurrentLinkedQueue<>();

	public void createClientHandler(Socket client) {

	}

	public void handleReceivedPacket(String name, BufferedReader in) {
		try {
			System.out.println(in.lines());
			System.out.println(in.ready());
			stringQueue.add(in.readLine());
			System.out.println("Result of data.get(i) inside mini-thread: " + stringQueue.peek());
		} catch (IOException e) {
			System.out.println("Unable to read result data");
		}
	}

	public String returnData() {
		return stringQueue.peek();
	}
}

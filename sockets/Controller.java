package sockets;

import constants.GeneralConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Controller {

	ConcurrentLinkedQueue<String> stringQueue = new ConcurrentLinkedQueue<>();

	public void handleReceivedPacket(String name, BufferedReader in) {
		try {
			stringQueue.add(in.readLine());
			System.out.println("The following data has been added to the queue: " + stringQueue.peek());

		} catch (IOException e) {
			System.out.println("Unable to read result data");

		}
	}

	public String returnData() {
		return stringQueue.peek();
	}
}

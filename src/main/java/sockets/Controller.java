package sockets;

import constants.GeneralConstants;
import logic.MultiplayerStates;
import scenes.BoardScene;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Controller {

	ConcurrentLinkedQueue<String> stringQueue = new ConcurrentLinkedQueue<>();
	MultiplayerStates ms = new MultiplayerStates();

	public void handleReceivedPacket(String name, BufferedReader in) {
		try {
			String data = in.readLine();
			stringQueue.add(data);

			System.out.println("The following data has been added to the queue: " + stringQueue.peek());

		} catch (IOException e) {
			System.out.println("Unable to read result data");

		}
	}

	public String returnData() {
		return stringQueue.poll();

	}

	public String peekData() {

	    return stringQueue.peek();
    }

}

package sockets;

import constants.GeneralConstants;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	boolean isConnected = false;
	Controller controller = new Controller();
	ClientHandler HC = null;

	public void startHost() {
		Thread host = new Thread(() -> {
			ServerSocket server = null;
			try {
				server = new ServerSocket(GeneralConstants.applicationPort);

			} catch (BindException e2) {
				System.out.println("Port Already in Use!");

			} catch (IOException e) {
				e.printStackTrace();

			}

			while (true) {

				if (server == null) {
					System.out.println("Null server");
					break;
				}
				try {
					Socket client = server.accept();

					System.out.println("Client Connected: " + isConnected);

					if (!isConnected) {
						HC = new ClientHandler("client", client, controller);
						isConnected = true;
						System.out.println("Client Connected: " + isConnected);
						HC.receive();

					} else {
                        HC.receive();

					}

				} catch (IOException e) {
					e.printStackTrace();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		host.setDaemon(true);
		host.start();

	}

	public String returnData() {

		return controller.returnData();
	}
}
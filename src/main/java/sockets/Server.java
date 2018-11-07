package sockets;

import constants.GeneralConstants;
import logic.GameCard;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	static boolean isConnected = false;
	Controller controller = new Controller();
	ClientHandler HC = null;
	static boolean stopHost = false;
    ServerSocket server = null;

	Socket client;

	Boolean amHost = false;

	public void startHost() {
		amHost = true;

		Thread host = new Thread(() -> {
			try {
				server = new ServerSocket(GeneralConstants.applicationPort);

			} catch (BindException e2) {
				System.out.println("Port Already in Use!");

			} catch (IOException e) {
				e.printStackTrace();

			}

			while (stopHost == false) {

				if (server == null) {
					System.out.println("Null server");
					break;
				}

				try {
					client = server.accept();

					System.out.println("Client Connected: " + isConnected);

					if (!isConnected) {
						HC = new ClientHandler("client", client, controller);
						isConnected = true;
						System.out.println("Client Connected: " + isConnected);
						HC.sendData("[Username] " + GeneralConstants.userName);
						HC.receive();

					} else {
                        HC.receive();

					}

				} catch (Exception e) {
					System.out.println("Server connection was closed, stopping server");
					server = null;

				}
			}
		});

		host.setDaemon(true);
		host.start();

	}

	public String returnData() {

		return controller.returnData();
	}

	public String peekData() {

		return controller.peekData();
	}

	public boolean sendData(String data) {
		if (HC == null) {
			return false;

		} else {
			return HC.sendData(data);

		}

    }

    public void stopHost() {
	    System.out.println("Stopping Host");
	    isConnected = false;

	    if (HC != null) {
	        System.out.println("Client Handler is active, stopping");
	        HC.close();

        } else {
	        System.out.println("Client Handler is not active");

        }

        if (server != null) {
            System.out.println("Server is active, stopping");
            try {
                server.close();

            } catch (IOException e) {
                System.out.println("Unable to stop server");

            }

        }

	    if (client != null) {
            try {
                client.close();

            } catch (IOException e) {
                System.out.println("Unable to close client");

            }
        } else {
	        System.out.println("Client is null");

        }

    }

    public boolean amHost() {
	    return amHost;

    }
}
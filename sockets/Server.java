package sockets;

import constants.GeneralConstants;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    boolean isConnected = false;
    Controller controller = new Controller();

    public void startHost() {
        Thread host = new Thread(() -> {
            Controller controller = new Controller();
            ServerSocket server = null;

            try {
                server = new ServerSocket(GeneralConstants.applicationPort);

            } catch (BindException e2) {
                System.out.println("Port Already in Use!");

            } catch (IOException e) {
                //do nothing

            }

            while (true) {
                if (server == null) { break; }

                try {
                    Socket client = server.accept();

                    System.out.println("Client Connected: " + isConnected);

                    if (!isConnected) {
                        controller.createClientHandler(client);
                        isConnected = true;
                        System.out.println("Client Connected: " + isConnected);
                    }

                } catch (IOException e) {
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
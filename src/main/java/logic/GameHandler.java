package logic;

import javafx.concurrent.Task;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import scenes.BoardScene;
import sockets.Client;
import sockets.Server;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class GameHandler{
    Boolean userConnected = false;
    Server server = null;
    Hyperlink userName = null;
    String test = "Yolo";
    AtomicReference<String> updatedName = new AtomicReference<>();
    AtomicReference<String> serverInitCards = new AtomicReference<>();
    AtomicReference<String> clientInitCards = new AtomicReference<>();
    GameMain gameController = null;
    Client client = null;

    public GameHandler(Server server, Hyperlink userName, GameMain gameController, Client client) {
        this.server = server;
        this.userName = userName;
        this.gameController = gameController;
        this.client = client;
        // store parameter for later user
    }

    @SuppressWarnings("Duplicates")
    public void updateUI() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while(true) {
                    String data = server.peekData();
                    if (data != null) {
                        String dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                        String dataContent = data.substring(data.indexOf("]") + 2);

                        if (dataType.equals("[Username]")) {
                            updatedName.set(dataContent);
                            server.returnData(); //remove the data instead of just peeking at it
                            break;

                        }
                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            userName.setText(updatedName.get());
            userConnected = true;
            sendServerCardData();

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();

    }

    @SuppressWarnings("Duplicates")
    public void updateClientUI() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while(true) {
                    String data = client.returnSocketData();
                    if (data != null) {
                        String dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                        String dataContent = data.substring(data.indexOf("]") + 2);

                        if (dataType.equals("[Username]")) {
                            updatedName.set(dataContent);
                            break;

                        }
                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            userName.setText(updatedName.get());
            userConnected = true;

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    @SuppressWarnings("Duplicates")
    public void updateGameBoard(GridPane playerRow, GridPane opponentRow) {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                boolean endStateOne = true;
                boolean endStateTwo = true;
                while(endStateOne || endStateTwo) {
                    String data = client.peekData();
                    if (data != null) {
                        String dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                        String dataContent = data.substring(data.indexOf("]") + 2);

                        if (dataType.equals("[InitServerCards]")) {
                            serverInitCards.set(dataContent);
                            client.returnSocketData(); //remove the data instead of just peeking at it
                            endStateOne = false;

                        } else if (dataType.equals("[InitClientCards]")) {
                            clientInitCards.set(dataContent);
                            client.returnSocketData();
                            endStateTwo = false;
                        }

                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            userConnected = true;
            generateClientScreen(playerRow, opponentRow);

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    public boolean getUserConnectedState() {

        return userConnected;
    }

    private void sendServerCardData() {
        String serverCards = gameController.getServerCards().get(0).value + "," + gameController.getServerCards().get(1).value;
        String clientCards = gameController.getOpponentHand().get(0).value + "," + gameController.getOpponentHand().get(1).value;

        System.out.println("Sent: " + serverCards);
        server.sendData("[InitServerCards] " + serverCards);
        server.sendData("[InitClientCards] " + clientCards);
    }

    private void generateClientScreen(GridPane playerRow, GridPane opponentRow) {
        String[] serverCards = serverInitCards.get().split(",");
        String[] clientCards = clientInitCards.get().split(",");

        GameCard opponentCardOne = new GameCard(serverCards[0], 1, true);
        GameCard opponentCardTwo = new GameCard(serverCards[1], 0, true);
        opponentRow.add(opponentCardOne.returnGameCardCover(), 0, 0);
        opponentRow.add(opponentCardTwo.returnGameCardCover(), 1, 0);

        GameCard playerCardOne = new GameCard(clientCards[0], 2, false);
        GameCard playerCardTwo = new GameCard(clientCards[1], 0, false);
        playerRow.add(playerCardOne.returnGameCardCover(), 0, 0);
        playerRow.add(playerCardTwo.returnGameCardCover(), 1, 0);


    }

}

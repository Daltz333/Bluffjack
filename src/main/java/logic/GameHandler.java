package logic;

import javafx.concurrent.Task;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import scenes.BoardScene;
import sockets.Client;
import sockets.Server;

import java.util.concurrent.atomic.AtomicReference;

public class GameHandler{
    private Boolean userConnected = false;
    private Server server = null;
    private Hyperlink userName = null;
    String test = "Yolo";
    private AtomicReference<String> updatedName = new AtomicReference<>();
    private AtomicReference<String> serverInitCards = new AtomicReference<>();
    private AtomicReference<String> clientInitCards = new AtomicReference<>();
    private GameMain gameController = null;
    private Client client = null;
    GridPane playerRow = null;
    GridPane opponentRow = null;

    public static boolean isWin = false;

    public GameHandler(Server server, Hyperlink userName, GameMain gameController, Client client, GridPane playerRow, GridPane opponentRow) {
        this.server = server;
        this.userName = userName;
        this.gameController = gameController;
        this.client = client;
        this.playerRow = playerRow;
        this.opponentRow = opponentRow;

        // store parameter for later user
    }

    //update host UI on client connect
    @SuppressWarnings("Duplicates")
    public void updateUI() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
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
            userName.setText(updatedName.get()); //update the client name
            userConnected = true;
            sendServerCardData();

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();

    }

    @SuppressWarnings("Duplicates")
    //update the UI on client -> server
    public void updateClientUI() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
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
            public Void call() {
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
            listenForServerCards();
            listenForTurnCompletionServer();

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    @SuppressWarnings("Duplicates")
    public void listenForTurnCompletionServer() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                while(true) {
                    String data = client.peekData();
                    if (data != null) {
                        String dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                        String dataContent = data.substring(data.indexOf("]") + 2);

                        if (dataType.equals("[TurnComplete]")) {
                            System.out.println("Turn completed!");
                            client.returnSocketData(); //remove the data instead of just peeking at it
                            break;
                        } else if (dataType.equals("[IsWin]")) {
                            System.out.println("Told to receive win!");
                            client.returnSocketData();
                            isWin = true;
                        } else {
                            System.out.println(data);
                            server.returnData();
                        }
                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            userConnected = true;
            BoardScene.setNotifierText("Your Turn!");
            BoardScene.updateTurnState(true);

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    @SuppressWarnings("Duplicates")
    public void listenForTurnCompletionClient() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                while(true) {
                    String data = server.peekData();
                    if (data != null) {
                        String dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                        String dataContent = data.substring(data.indexOf("]") + 2);

                        if (dataType.equals("[TurnComplete]")) {
                            System.out.println("Turn completed");
                            server.returnData(); //remove the data instead of just peeking at it
                            break;
                        } else if (dataType.equals("[IsWin]")) {
                            server.returnData();
                            System.out.println("Told to receive win!");
                            isWin = true;
                        } else {
                            System.out.println(data);
                            //if we don't know what the fuck the data is
                            //just yeet it into the abyss
                            server.returnData();
                        }
                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            userConnected = true;
            BoardScene.setNotifierText("Your Turn!");
            BoardScene.updateTurnState(true);

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    public boolean getUserConnectedState() {

        return userConnected;
    }

    @SuppressWarnings("Duplicates")
    public void listenForServerCards() {
        AtomicReference<String> tempData = new AtomicReference<>();
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                while(true) {
                    String data = client.peekData();
                    if (data != null) {
                        String dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                        String dataContent = data.substring(data.indexOf("]") + 2);

                        if (dataType.equals("[NewCard]")) {
                            tempData.set(dataContent);
                            System.out.println("Received Data: " + dataContent);
                            client.returnSocketData(); //remove the data instead of just peeking at it
                            break;

                        }

                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            userConnected = true;
            gameController.addCard(playerRow, opponentRow, Integer.valueOf(tempData.get()), true);
            listenForServerCards();

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    @SuppressWarnings("Duplicates")
    public void listenForClientCards() {
        AtomicReference<String> tempData = new AtomicReference<>();
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                while(true) {
                    String data = server.peekData();
                    if (data != null) {
                        String dataType = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                        String dataContent = data.substring(data.indexOf("]") + 2);

                        if (dataType.equals("[NewCard]")) {
                            tempData.set(dataContent);
                            System.out.println("Received Data: " + dataContent);
                            server.returnData(); //remove the data instead of just peeking at it
                            break;

                        }

                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            userConnected = true;
            gameController.addCard(playerRow, opponentRow, Integer.valueOf(tempData.get()), true);
            listenForClientCards();

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    //send our cards generated by the server to the client
    private void sendServerCardData() {
        String serverCards = gameController.getServerCards().get(0).value + "," + gameController.getServerCards().get(1).value;
        String clientCards = gameController.getOpponentHand().get(0).value + "," + gameController.getOpponentHand().get(1).value;

        System.out.println("Sent: " + serverCards);
        server.sendData("[InitServerCards] " + serverCards);
        server.sendData("[InitClientCards] " + clientCards);
    }

    //creates the gameboard for the client
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

        gameController.setExclusions(Integer.valueOf(clientCards[0]));
        gameController.setExclusions(Integer.valueOf(clientCards[1]));
        gameController.setExclusions(Integer.valueOf(serverCards[0]));
        gameController.setExclusions(Integer.valueOf(serverCards[1]));

    }

}

package logic;

import javafx.concurrent.Task;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import sockets.Server;

import java.util.concurrent.atomic.AtomicReference;

public class GameHandler{
    Boolean userConnected = false;
    Server server = null;
    Hyperlink userName = null;
    String test = "Yolo";
    AtomicReference<String> updatedName = new AtomicReference<>();

    public GameHandler(Server server, Hyperlink userName) {
        this.server = server;
        this.userName = userName;
        // store parameter for later user
    }

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

        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();

    }

    public boolean getUserConnectedState() {

        return userConnected;
    }

    private void generateOpponentScreen() {

    }

}

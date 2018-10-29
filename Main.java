import constants.GeneralConstants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameMain;
import scenes.SceneController;
import sockets.Server;
import styles.java.RootStyles;

import java.io.IOException;

public class Main extends Application {

    private RootStyles rootStyles = new RootStyles();
    private SceneController sceneController = new SceneController();
    private Server server = new Server();

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        //set stage information
        primaryStage.setTitle(GeneralConstants.projectName);
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();

        //pass main root to scene controller
        SceneController.root = root;

        rootStyles.setRootStyles(root);
        SceneController.manageScenes();

        //set fixed aspect ratio
        primaryStage.setMinHeight(GeneralConstants.minWindowHeight);
        primaryStage.setMinWidth(GeneralConstants.minWindowWidth);

        //have subroots fill root completely
        ColumnConstraints column1 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        column1.setPercentWidth(100);
        row1.setPercentHeight(100);
        root.getColumnConstraints().add(column1);
        root.getRowConstraints().add(row1);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.out.println("Stopped Process");
                Platform.exit();
                System.exit(0);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void stop() {

    }
}

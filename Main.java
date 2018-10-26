import bluffjack.constants.GeneralConstants;
import bluffjack.scenes.SceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import styles.java.RootStyles;

public class Main extends Application {

    RootStyles rootStyles = new RootStyles();
    SceneController sceneController = new SceneController();

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        primaryStage.setTitle(GeneralConstants.projectName);
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();

        rootStyles.setRootStyles(root);
        sceneController.manageScenes(root);

        //set fixed aspect ratio
        primaryStage.setMinHeight(GeneralConstants.minWindowHeight);
        primaryStage.setMinWidth(GeneralConstants.minWindowWidth);

        ColumnConstraints column1 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        column1.setPercentWidth(100);
        row1.setPercentHeight(100);
        root.getColumnConstraints().add(column1);
        root.getRowConstraints().add(row1);

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        //
    }
}

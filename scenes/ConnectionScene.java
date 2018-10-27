package scenes;

import constants.GeneralConstants;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import styles.java.ConnectionStyles;

public class ConnectionScene {

    Hyperlink connectionButton = new Hyperlink(GeneralConstants.connectDialogName);
    Hyperlink hostGameButton = new Hyperlink(GeneralConstants.hostDialogName);
    Hyperlink mainMenuButton = new Hyperlink(GeneralConstants.mainMenuButton);

    Label titleLabel = new Label(GeneralConstants.projectName);

    SceneController sceneController = new SceneController();

    ConnectionStyles connectionStyles = new ConnectionStyles();

    TextField ipBox = new TextField();
    Hyperlink confirmationButton = new Hyperlink(GeneralConstants.connectDialogName);
    Hyperlink returnButton = new Hyperlink(GeneralConstants.returnButtonName);

    public void setSceneState(GridPane root) {
        GridPane sceneRoot = new GridPane();
        GridPane dialog = new GridPane();

        titleLabel.setId("titleLabel");

        GridPane.setHalignment(connectionButton, HPos.CENTER);
        GridPane.setHalignment(hostGameButton, HPos.CENTER);
        GridPane.setHalignment(mainMenuButton, HPos.CENTER);
        dialog.setId("dialogMain");
        sceneRoot.setAlignment(Pos.CENTER);

        connectionButton.setId("connectionButton");
        hostGameButton.setId("connectionButton");
        mainMenuButton.setId("connectionButton");

        connectionStyles.setConnectionStyles(sceneRoot);

        dialog.add(titleLabel, 0, 0);
        dialog.add(hostGameButton, 0, 1);
        dialog.add(connectionButton, 0, 2);
        dialog.add(mainMenuButton, 0, 3);
        dialog.setAlignment(Pos.CENTER);

        sceneRoot.add(dialog, 0, 0);

        root.add(sceneRoot, 0,0);

        setLinkHandlers();

    }

    private void setLinkHandlers() {
        mainMenuButton.setOnAction(event -> {
            sceneController.setScene(0);
        });

        connectionButton.setOnAction(event -> {
            sceneController.setScene(2);
        });

        returnButton.setOnAction(event -> {
            sceneController.setScene(1);
        });

    }

    public void showConnectScene(GridPane root) {
        GridPane sceneRoot = new GridPane();
        GridPane dialog = new GridPane();
        GridPane topDialog = new GridPane();

        titleLabel.setId("titleLabel");
        ipBox.setId("ipBox");
        confirmationButton.setId("ipButton");
        returnButton.setId("ipButton");
        dialog.setId("dialogSecond");

        GridPane.setHalignment(titleLabel, HPos.CENTER);
        GridPane.setHalignment(ipBox, HPos.CENTER);
        GridPane.setHalignment(confirmationButton, HPos.CENTER);
        GridPane.setHalignment(returnButton, HPos.CENTER);

        sceneRoot.setAlignment(Pos.CENTER);
        topDialog.setAlignment(Pos.CENTER);


        connectionStyles.setConnectionStyles(sceneRoot);

        //dialog.setGridLinesVisible(true);


        topDialog.add(titleLabel, 0, 0);
        topDialog.add(ipBox, 0, 1);
        dialog.add(confirmationButton, 0, 0);
        dialog.add(returnButton, 1, 0);
        dialog.setAlignment(Pos.CENTER);

        sceneRoot.add(topDialog, 0, 0);
        sceneRoot.add(dialog, 0, 1);

        root.add(sceneRoot, 0,0);


    }

}

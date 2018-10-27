package scenes;

import constants.GeneralConstants;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import styles.java.TitleStyles;

import java.util.Optional;

public class TitleScene {
    BorderPane subRoot = new BorderPane();
    GridPane mainContentPane = new GridPane();
    GridPane bottomBarPane = new GridPane();

    Label titleLabel = new Label(GeneralConstants.projectName);
    Hyperlink startGame = new Hyperlink(GeneralConstants.startGame);
    Hyperlink optionsGame = new Hyperlink(GeneralConstants.optionsLinkName);
    Hyperlink exitGame = new Hyperlink(GeneralConstants.exitGameName);
    Hyperlink credits = new Hyperlink(GeneralConstants.creditsOptionName);
    Label versionNumber = new Label("Version: " + GeneralConstants.versionNumber);
    TitleStyles titleStyles = new TitleStyles();
    Label content = new Label(GeneralConstants.creditsText);

    public void setSceneState(GridPane root) {

        if (mainContentPane.getChildren().isEmpty()) {
            System.out.println("Title Pane is empty");

            titleStyles.setTitleStyles(subRoot);
            titleLabel.setId("titleLabel");
            startGame.setId("menuItem");
            optionsGame.setId("menuItem");
            exitGame.setId("menuItem");
            credits.setId("smallMenuItem");
            versionNumber.setId("smallMenuItem");
            content.setId("creditContent");

            GridPane.setHalignment(versionNumber, HPos.RIGHT);
            GridPane.setHalignment(credits, HPos.RIGHT);
            GridPane.setHalignment(startGame, HPos.CENTER);
            GridPane.setHalignment(optionsGame, HPos.CENTER);
            GridPane.setHalignment(exitGame, HPos.CENTER);

            bottomBarPane.setAlignment(Pos.BOTTOM_RIGHT);

            mainContentPane.setAlignment(Pos.CENTER);
            mainContentPane.add(titleLabel, 0, 0);
            mainContentPane.add(startGame, 0, 1);
            mainContentPane.add(optionsGame, 0, 2);
            mainContentPane.add(exitGame, 0, 3);
            bottomBarPane.add(versionNumber, 0, 0);
            bottomBarPane.add(credits, 0, 1);

            subRoot.setCenter(mainContentPane);
            subRoot.setBottom(bottomBarPane);
            root.add(subRoot, 0, 0);

            setLinkHandlers();

        } else {
            System.out.println("Title pane is not empty");
            root.add(subRoot, 0, 0);
        }

    }

    public void setLinkHandlers() {
        exitGame.setOnAction(event -> {
            Platform.exit();
        });

        startGame.setOnAction(event -> {
            System.out.println("Show IP Dialog");
            SceneController sceneController = new SceneController();
            sceneController.setScene(1);

        });

        credits.setOnAction(event -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Additional Credits");
            dialog.setHeaderText("Extra thanks to the following people:");
            titleStyles.setDialogPaneStyle(dialog.getDialogPane());

            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/images/heart.png").toString()));

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            content.setId("creditContent");
            grid.add(content, 0, 0);

            dialog.getDialogPane().setContent(grid);

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

            Optional<Pair<String, String>> result = dialog.showAndWait();


            dialog.close();
        });

    }
}

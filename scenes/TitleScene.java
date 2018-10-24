package bluffjack.scenes;

import bluffjack.constants.GeneralConstants;
import bluffjack.styles.java.TitleStyles;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TitleScene {
    VBox subRoot = new VBox();
    GridPane mainContentPane = new GridPane();
    GridPane bottomBarPane = new GridPane();

    Label titleLabel = new Label(GeneralConstants.projectName);
    Hyperlink startGame = new Hyperlink(GeneralConstants.startGame);
    Hyperlink optionsGame = new Hyperlink(GeneralConstants.optionsLinkName);
    Hyperlink exitGame = new Hyperlink(GeneralConstants.exitGameName);
    Label credits = new Label(GeneralConstants.creditsOptionName);
    TitleStyles titleStyles = new TitleStyles();

    public void setSceneState(GridPane root) {
        titleStyles.setTitleStyles(mainContentPane);
        titleLabel.setId("titleLabel");
        startGame.setId("menuItem");
        optionsGame.setId("menuItem");
        exitGame.setId("menuItem");
        credits.setId("smallTextItem");

        GridPane.setHalignment(credits, HPos.RIGHT);
        GridPane.setHalignment(startGame, HPos.CENTER);
        GridPane.setHalignment(optionsGame, HPos.CENTER);
        GridPane.setHalignment(exitGame, HPos.CENTER);

        bottomBarPane.setAlignment(Pos.BOTTOM_RIGHT);

        mainContentPane.add(titleLabel, 0, 0);
        mainContentPane.add(startGame, 0, 1);
        mainContentPane.add(optionsGame, 0, 2);
        mainContentPane.add(exitGame, 0, 3);
        bottomBarPane.add(credits, 0, 0);

        subRoot.getChildren().add(mainContentPane);
        subRoot.getChildren().add(bottomBarPane);
        root.add(subRoot, 0, 0);

        setLinkHandlers();

        }

        public void setLinkHandlers() {
            exitGame.setOnAction(event -> {
                Platform.exit();
            });

        }
}

package scenes;

import constants.GeneralConstants;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import logic.GameCard;
import logic.GameMain;
import styles.java.BoardStyles;

public class BoardScene {
    BorderPane mainBoard = new BorderPane();
    BorderPane opponentSide = new BorderPane();
    BorderPane board = new BorderPane();
    GridPane playerSide = new GridPane();
    GridPane playerDeck = new GridPane();
    GridPane opponentDeck = new GridPane();

    BoardStyles boardStyles = new BoardStyles();

    SceneController sceneController = new SceneController();

    GameMain gameController = new GameMain();

    Hyperlink HitMeOption = new Hyperlink(GeneralConstants.hitMeName);
    Hyperlink PassOption = new Hyperlink(GeneralConstants.passName);
    Hyperlink SpecialOption = new Hyperlink(GeneralConstants.specialsName);

    Hyperlink playerName = new Hyperlink("Opponent: Game Bot");
    Hyperlink exitGame = new Hyperlink(GeneralConstants.mainMenuButton);

    public void setMainBoard(GridPane root) {

        if(mainBoard.getChildren().isEmpty()) {
            boardStyles.setBoardtyles(mainBoard);
            mainBoard.setId("mainBoard");
            playerSide.setId("playerRoot");
            opponentSide.setId("playerRoot");

            playerSide.setAlignment(Pos.BOTTOM_CENTER);
            playerDeck.setAlignment(Pos.CENTER);


            mainBoard.setTop(opponentSide);
            mainBoard.setCenter(board);
            mainBoard.setBottom(playerSide);
            mainBoard.setLeft(playerDeck);
            mainBoard.setRight(opponentDeck);

            createCenter(board);
            createDeck(playerDeck, opponentDeck);
            createPlayerSide(playerSide);
            createOpponentSide(opponentSide);

            setEventHandlers();

            root.add(mainBoard, 0, 0);

        } else {
            root.add(mainBoard, 0, 0);

        }

    }

    private void createDeck(GridPane playerSide, GridPane opponentSide) {
        GameCard playerDeck = new GameCard("Deck", 0);

        playerSide.add(playerDeck.returnGameCardCover(), 0, 0);

    }

    private void createCenter(BorderPane board) {
        GridPane playerRow = new GridPane();
        GridPane opponentRow = new GridPane();

        gameController.startGame(playerRow, opponentRow);

        board.setBottom(playerRow);

    }

    private void createPlayerSide(GridPane playerSide) {
        HitMeOption.setId("playerOption");
        PassOption.setId("playerOption");
        SpecialOption.setId("playerOption");

        playerSide.add(HitMeOption, 0, 0);
        playerSide.add(PassOption, 1, 0);
        playerSide.add(SpecialOption, 2, 0);

    }

    private void createOpponentSide(BorderPane opponentSide) {
        playerName.setId("opponentPlayer");
        exitGame.setId("playerOption");

        GridPane.setHalignment(exitGame, HPos.LEFT);
        GridPane.setHalignment(playerName, HPos.RIGHT);

        opponentSide.setLeft(exitGame);
        opponentSide.setCenter(playerName);

    }


    private void optionsButton(GridPane board) {


    }

    private void setEventHandlers() {
        exitGame.setOnAction(event -> {
            sceneController.setScene(0);
        });
    }

}

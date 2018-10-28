package scenes;

import constants.GeneralConstants;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import logic.GameCard;
import styles.java.BoardStyles;

public class BoardScene {
    BorderPane mainBoard = new BorderPane();
    GridPane opponentSide = new GridPane();
    GridPane board = new GridPane();
    GridPane playerSide = new GridPane();
    GridPane playerDeck = new GridPane();
    GridPane opponentDeck = new GridPane();

    BoardStyles boardStyles = new BoardStyles();

    public void setMainBoard(GridPane root) {
        boardStyles.setBoardtyles(mainBoard);
        mainBoard.setId("mainBoard");
        playerSide.setId("playerRoot");
        opponentSide.setId("playerRoot");

        opponentSide.setAlignment(Pos.TOP_CENTER);
        playerSide.setAlignment(Pos.BOTTOM_CENTER);
        playerDeck.setAlignment(Pos.CENTER);


        mainBoard.setTop(opponentSide);
        mainBoard.setCenter(board);
        mainBoard.setBottom(playerSide);
        mainBoard.setLeft(playerDeck);
        mainBoard.setRight(opponentDeck);

        createDeck(playerDeck, opponentDeck);
        createPlayerSide(playerSide);
        createOpponentSide(opponentSide);

        root.add(mainBoard, 0, 0);

    }

    private void createDeck(GridPane playerSide, GridPane opponentSide) {
        GameCard playerDeck = new GameCard(0, 0);

        playerSide.add(playerDeck.returnGameCardCover(), 0, 0);


    }

    private void createPlayerSide(GridPane playerSide) {
        Hyperlink HitMeOption = new Hyperlink(GeneralConstants.hitMeName);
        Hyperlink PassOption = new Hyperlink(GeneralConstants.passName);
        Hyperlink SpecialOption = new Hyperlink(GeneralConstants.specialsName);

        HitMeOption.setId("playerOption");
        PassOption.setId("playerOption");
        SpecialOption.setId("playerOption");

        playerSide.add(HitMeOption, 0, 0);
        playerSide.add(PassOption, 1, 0);
        playerSide.add(SpecialOption, 2, 0);


    }

    private void createOpponentSide(GridPane opponentSide) {
        Hyperlink PlayerName = new Hyperlink("Opponent: Game Bot");
        PlayerName.setId("playerOption");

        opponentSide.setGridLinesVisible(true);

        Hyperlink exitGame = new Hyperlink(GeneralConstants.mainMenuButton);

        GridPane.setHalignment(exitGame, HPos.LEFT);
        GridPane.setHalignment(PlayerName, HPos.RIGHT);

        opponentSide.add(exitGame, 0, 0);
        opponentSide.add(PlayerName, 1, 0);

    }


    private void optionsButton(GridPane board) {


    }

}

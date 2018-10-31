package scenes;

import constants.GeneralConstants;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import logic.GameCard;
import logic.GameMain;
import sockets.Controller;
import sockets.Server;
import styles.java.BoardStyles;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BoardScene {
	// framework of board screen
	BorderPane mainBoard = new BorderPane();
	BorderPane opponentSide = new BorderPane();
	BorderPane board = new BorderPane();
	GridPane playerSide = new GridPane();
	GridPane playerDeck = new GridPane();
	GridPane opponentDeck = new GridPane();

	BoardStyles boardStyles = new BoardStyles();

	SceneController sceneController = new SceneController();

	GameMain gameController = new GameMain();

	// buttons
	Hyperlink HitMeOption = new Hyperlink(GeneralConstants.hitMeName);
	Hyperlink PassOption = new Hyperlink(GeneralConstants.passName);
	Hyperlink SpecialOption = new Hyperlink(GeneralConstants.specialsName);

	Hyperlink playerName = new Hyperlink("Opponent: Game Bot");
	Hyperlink exitGame = new Hyperlink(GeneralConstants.mainMenuButton);

	// sub panes of center board
	GridPane playerRow = new GridPane();
	GridPane opponentRow = new GridPane();

	Alert alert = new Alert(Alert.AlertType.INFORMATION);

	Server server = new Server();

	public void setMainBoard(GridPane root) {

		// check if board was previously created, prevent null pointer exception
		if (mainBoard.getChildren().isEmpty()) {

			server.startHost();
			// set our styles
			boardStyles.setBoardStyles(mainBoard);
			mainBoard.setId("mainBoard");
			playerSide.setId("playerRoot");
			opponentSide.setId("playerRoot");

			// set alignments
			playerSide.setAlignment(Pos.BOTTOM_CENTER);
			playerDeck.setAlignment(Pos.CENTER);

			// set positions
			mainBoard.setTop(opponentSide);
			mainBoard.setCenter(board);
			mainBoard.setBottom(playerSide);
			mainBoard.setLeft(playerDeck);
			mainBoard.setRight(opponentDeck);

			// create board
			createCenter(board);
			createDeck(playerDeck);
			createPlayerSide(playerSide);
			createOpponentSide(opponentSide);

			setEventHandlers();

			// set right pane to be equal to left pane
			opponentDeck.setMinWidth(GeneralConstants.cardWidth);

			root.add(mainBoard, 0, 0);

		} else {
			// redisplay board and restart game
			gameController.startGame(playerRow, opponentRow);
			root.add(mainBoard, 0, 0);

		}

	}

	private void createDeck(GridPane playerSide) {
		// creates the "visual" deck on the board, no functional purpose
		GameCard playerDeck = new GameCard("Deck", 0);

		playerSide.add(playerDeck.returnGameCardCover(), 0, 0);

	}

	private void createCenter(BorderPane board) {
		// create the center of the boards
		gameController.startGame(playerRow, opponentRow);

		playerRow.setId("playerRow");
		board.setBottom(playerRow);

	}

	private void createPlayerSide(GridPane playerSide) {
		// user options
		HitMeOption.setId("playerOption");
		PassOption.setId("playerOption");
		SpecialOption.setId("playerOption");

		playerSide.add(HitMeOption, 0, 0);
		playerSide.add(PassOption, 1, 0);
		playerSide.add(SpecialOption, 2, 0);

	}

	private void createOpponentSide(BorderPane opponentSide) {
		// opponent side visuals
		playerName.setId("opponentPlayer");
		exitGame.setId("playerOption");

		GridPane.setHalignment(exitGame, HPos.LEFT);
		GridPane.setHalignment(playerName, HPos.RIGHT);

		opponentSide.setLeft(exitGame);
		opponentSide.setCenter(playerName);

	}

	private void setEventHandlers() {
		// handle our buttons here
		exitGame.setOnAction(event -> {
			gameController.stopGame(playerRow);
			sceneController.setScene(0);
		});

		HitMeOption.setOnAction(event -> {
			gameController.giveCard(playerRow);

		});

		PassOption.setOnAction(event -> {
			alert.setTitle("Message Alert");
			alert.setHeaderText("The following message was sent from the client");
			server.sendData("Test");
			alert.setContentText(server.returnData());
			alert.showAndWait();

		});
	}

}

package logic;

import constants.GeneralConstants;
import helpers.Utilities;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sockets.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameMain {

	private boolean givenInitialCards = false;
	private Random rand = new Random();

	private ArrayList<Integer> exclusions = new ArrayList<Integer>();
	private ArrayList<GameCard> deck = GameCard.generateGameDeck();
	private ArrayList<GameCard> playerHand = new ArrayList<>();
	private ArrayList<GameCard> opponentHand = new ArrayList<GameCard>();

	// Server serverConnection = new Server();

	public void startGame(GridPane playerRow, GridPane opponentRow) {
		// close when application closes

		if (!givenInitialCards) {
			// grab random VALID int, create new card of that int
			int cardOne = Utilities.getRandomWithExclusion(rand, 1, 11, exclusions);
			exclusions.add(cardOne);

			int opponentCardOne = Utilities.getRandomWithExclusion(rand, 1, 11, exclusions);
			exclusions.add(opponentCardOne);

			int cardTwo = Utilities.getRandomWithExclusion(rand, 1, 11, exclusions);
			exclusions.add(cardTwo);

            int opponentCardTwo = Utilities.getRandomWithExclusion(rand, 1, 11, exclusions);
            exclusions.add(opponentCardTwo);

			// set first card object to have overlay *temporary*
			GameCard cardObjectOne = new GameCard(Integer.toString(cardOne), 2, false);
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setBrightness(-0.2);
			colorAdjust.setSaturation(0.8);
			colorAdjust.setHue(-0.05);
			cardObjectOne.returnGameCardCover().setEffect(colorAdjust);

            GameCard cardObjectOneOpponent = new GameCard(Integer.toString(opponentCardOne), 1, true);
            GameCard cardObjectTwoOpponent = new GameCard(Integer.toString(opponentCardTwo), 0, true);

			GameCard cardObjectTwo = new GameCard(Integer.toString(cardTwo), 0, false);

			playerHand.add(cardObjectOne);
			playerHand.add(cardObjectTwo);

			opponentHand.add(cardObjectOneOpponent);
            opponentHand.add(cardObjectTwoOpponent);

			opponentRow.add(cardObjectOneOpponent.returnGameCardCover(), 0, 0);
            opponentRow.add(cardObjectTwoOpponent.returnGameCardCover(), 1, 0);

            opponentRow.setAlignment(Pos.CENTER);
            opponentRow.setHgap(GeneralConstants.deckHGap);
            opponentRow.setGridLinesVisible(GeneralConstants.debugEnabled);

			playerRow.setAlignment(Pos.CENTER);
			playerRow.setHgap(GeneralConstants.deckHGap);

			playerRow.setGridLinesVisible(GeneralConstants.debugEnabled);

			playerRow.add(cardObjectOne.returnGameCardCover(), 0, 0);
			playerRow.add(cardObjectTwo.returnGameCardCover(), 1, 0);

			givenInitialCards = true;

		}

    }

	// logic to displaying card in specified row
	public void giveCard(GridPane playerRow) {
		int cardInt = Utilities.getRandomWithExclusion(rand, 1, 11, exclusions);

		System.out.println(exclusions);
		System.out.println("Random card is: " + cardInt);

		// do not proceed if no cards left in deck
		if (!(cardInt == -1)) {
			GameCard cardObjectTwo = new GameCard(Integer.toString(cardInt), 0, false);
			this.exclusions.add(cardInt);

			boolean exit = false;
			int i = 0;

			// check next available index of row
			while (!exit) {
				try {
					playerRow.getChildren().get(i);
					// do nothing
				} catch (Exception e) {
					System.out.println("Empty Index is: " + i);
					exit = true;
					break;
				}
				i++;
			}

			playerRow.add(cardObjectTwo.returnGameCardCover(), i, 0);

		} else {
			// do nothing

		}

	}

	public void stopGame(GridPane playerRow) {
		exclusions.clear();
		deck.clear();
		opponentHand.clear();
		playerHand.clear();
		givenInitialCards = false;

		playerRow.getChildren().clear();

	}

	public void giveInitialCardClient() {

	}

	ArrayList<GameCard> getServerCards() {
	    return playerHand;
    }

    ArrayList<GameCard> getOpponentHand() {
	    return opponentHand;
    }

    void setExclusions(int exclusion) {
	    exclusions.add(exclusion);
    }

}

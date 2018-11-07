package logic;

import constants.GeneralConstants;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class GameCard extends Region {

	ImageView coverImage;
	private Button imageButton;
	String value;
	private boolean opponent;

	// card constructor
	public GameCard(String cardNum, int cardType, boolean opponent) {
		this.value = cardNum;
		this.opponent = opponent;

		if (cardType == 1) {
			//front of card, blank
			imageButton = new Button();
			if (opponent) {
				imageButton.setId("gameCardFrontOpponent");
			} else {
				imageButton.setId("gameCardFront");
			}
		} else if (cardType == 2) {
			//front of card with number, for player
			imageButton = new Button(cardNum);
			if (opponent) {
				imageButton.setId("gameCardFrontOpponent");
			} else {
				imageButton.setId("gameCardFront");
			}
		} else {
			imageButton = new Button(cardNum);
			if (opponent) {
				imageButton.setId("gameCardBackOpponent");
			} else {
				imageButton.setId("gameCardBack");
			}
		}

		String stylesheet = getClass().getClassLoader().getResource("css/CardStyles.css").toExternalForm();
		imageButton.getStylesheets().add(stylesheet);

		imageButton.setPrefSize(GeneralConstants.cardWidth, GeneralConstants.cardHeight);
	}

	public Button returnGameCardCover() {
		return this.imageButton;

	}

	static ArrayList<GameCard> generateGameDeck() {
		ArrayList<GameCard> cardArray = new ArrayList<>();

		// create our array of game cards
		for (int i = 0; i < 12; i++) {
			cardArray.add(new GameCard(String.valueOf(i), 0, false));
		}

		return cardArray;
	}

}

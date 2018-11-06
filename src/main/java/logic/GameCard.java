package logic;

import constants.GeneralConstants;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class GameCard extends Region {

	ImageView coverImage;
	Button imageButton;
	String value;

	private String stylesheet = getClass().getClassLoader().getResource("css/CardStyles.css").toExternalForm();

	// card constructor
	public GameCard(String cardNum, int cardType, boolean opponent) {
		this.value = cardNum;

		if (cardType == 1) {
			imageButton = new Button();
			if (opponent) {
				imageButton.setId("gameCardFrontOpponent");
			} else {
				imageButton.setId("gameCardFront");
			}
		} else if (cardType == 2) {
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

		imageButton.getStylesheets().add(stylesheet);

		imageButton.setPrefSize(GeneralConstants.cardWidth, GeneralConstants.cardHeight);
	}

	public Button returnGameCardCover() {
		return this.imageButton;

	}

	public static ArrayList<GameCard> generateGameDeck() {
		ArrayList<GameCard> cardArray = new ArrayList<>();

		// create our array of game cards
		for (int i = 0; i < 12; i++) {
			cardArray.add(new GameCard(String.valueOf(i), 0, false));
		}

		return cardArray;
	}

}

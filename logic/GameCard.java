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

    private String stylesheet = this.getClass().getResource("/styles/css/CardStyles.css").toExternalForm();

    //card constructor
    public GameCard(String cardNum, int cardType) {
        this.value = cardNum;

        imageButton = new Button(cardNum);

        imageButton.getStylesheets().add(stylesheet);

        imageButton.setPrefSize(GeneralConstants.cardWidth, GeneralConstants.cardHeight);
        imageButton.setId("gameCard");
    }

    public Button returnGameCardCover() {
        return this.imageButton;

    }

    public static ArrayList<GameCard> generateGameDeck() {
        ArrayList<GameCard> cardArray = new ArrayList<>();

        //create our array of game cards
        for (int i = 0; i < 12; i++){
            cardArray.add(new GameCard(String.valueOf(i), 0 ));
        }

        return cardArray;
    }

}

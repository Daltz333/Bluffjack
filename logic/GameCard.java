package logic;

import constants.GeneralConstants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class GameCard extends Region {

    int value;
    ImageView coverImage;

    //card constructor
    public GameCard(int cardNum, int cardType) {
        setId("gameCard");

        Image cover = new Image(getClass().getResourceAsStream("/images/game_card_back.jpg"));
        this.coverImage = new ImageView();
        this.coverImage.setImage(cover);
        this.coverImage.setPreserveRatio(true);
        this.coverImage.setFitWidth(GeneralConstants.cardSize);
        this.value = cardNum;
    }

    public ImageView returnGameCardCover() {
        return this.coverImage;

    }

    public ArrayList<GameCard> generateGameCardFront() {
        ArrayList<GameCard> cardArray = new ArrayList<>();

        //create our array of game cards
        for (int i = 0; i < 12; i++){
            cardArray.add(new GameCard(i, 0 ));
        }

        return cardArray;
    }


    public ArrayList<GameCard> generateGameCardBack() {
        ArrayList<GameCard> cardArray = new ArrayList<>();

        //create our array of game cards
        for (int i = 0; i < 12; i++){
            cardArray.add(new GameCard(i, 1));
        }

        return cardArray;
    }
}

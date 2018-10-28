package logic;

import helpers.Utilities;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

public class GameMain {

    boolean givenInitialCards = false;
    Random rand = new Random();

    ArrayList<Integer> exclusions = new ArrayList<Integer>();

    GameCard cardHidden;

    public void startGame(GridPane playerRow, GridPane opponentRow) {
        ArrayList<GameCard> deck = GameCard.generateGameDeck();

        if (givenInitialCards = false) {
            int cardOne = rand.nextInt(11) + 0;
            exclusions.add(cardOne);

            int cardTwo = Utilities.getRandomWithExclusion(rand, 0, 11, exclusions);

        } else {

        }

    }


    public void stopGame() {

    }


}

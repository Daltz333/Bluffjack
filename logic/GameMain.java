package logic;

import constants.GeneralConstants;
import helpers.Utilities;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class GameMain {

    private boolean givenInitialCards = false;
    private Random rand = new Random();

    private ArrayList<Integer> exclusions = new ArrayList<Integer>();
    private ArrayList<GameCard> deck = GameCard.generateGameDeck();
    private ArrayList<GameCard> playerHand = new ArrayList<>();
    private ArrayList<GameCard> opponentHand = new ArrayList<GameCard>();

    public void startGame(GridPane playerRow, GridPane opponentRow) {
        if (!givenInitialCards) {
            //grab random VALID int, create new card of that int
            int cardOne = rand.nextInt(11);
            exclusions.add(cardOne);

            int cardTwo = Utilities.getRandomWithExclusion(rand, 0, 11, exclusions);
            exclusions.add(cardTwo);

            //set first card object to have overlay *temporary*
            GameCard cardObjectOne = new GameCard(Integer.toString(cardOne), 0);
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.2);
            colorAdjust.setSaturation(0.8);
            colorAdjust.setHue(-0.05);
            cardObjectOne.returnGameCardCover().setEffect(colorAdjust);

            GameCard cardObjectTwo = new GameCard(Integer.toString(cardTwo), 0);

            playerHand.add(cardObjectOne);
            playerHand.add(cardObjectTwo);

            playerRow.setAlignment(Pos.CENTER);
            playerRow.setHgap(GeneralConstants.deckHGap);

            playerRow.setGridLinesVisible(GeneralConstants.debugEnabled);

            playerRow.add(cardObjectOne.returnGameCardCover(), 0, 0);
            playerRow.add(cardObjectTwo.returnGameCardCover(), 1, 0);

            givenInitialCards = true;

        } else {

        }

    }

    //logic to displaying card in specified row
    public void giveCard(GridPane playerRow) {
        int cardInt = Utilities.getRandomWithExclusion(rand, 0, 11, exclusions);

        System.out.println(exclusions);
        System.out.println("Random card is: " + cardInt);

        //do not proceed if no cards left in deck
        if (!(cardInt == -1)) {
            GameCard cardObjectTwo = new GameCard(Integer.toString(cardInt), 0);
            this.exclusions.add(cardInt);

            boolean exit = false;
            int i = 0;

            //check next available index of row
            while (!exit) {
                try {
                    playerRow.getChildren().get(i);
                    //do nothing
                } catch(Exception e) {
                    System.out.println("Empty Index is: " + i);
                    exit = true;
                    break;
                }
                i++;
            }

            playerRow.add(cardObjectTwo.returnGameCardCover(), i, 0);

        } else {
            //do nothing

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


}

import cs3500.hw02.Rank;
import cs3500.hw02.StandardCard;
import cs3500.hw02.Suit;
import cs3500.hw03.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by baharsheikhi on 5/28/16.
 */
public class WhistControllerTest {

    List<StandardCard> basicDeck;

    StringBuffer out;
    StringBuffer outInvalidKey;
    StringBuffer outInvalidNumber;
    StringBuffer outQuit;

    WhistModel nullModel;
    WhistModel model1;
    WhistModel basicModel;

    WhistController controller1;
    WhistController controllerInvalidNumber;
    WhistController controllerInvalidKey;
    WhistController controllerQuit;


    private void initData() {

         this.basicDeck = new ArrayList<StandardCard>();

        for (int i = StandardCard.MIN_RANK_VALUE; i <= StandardCard.MAX_RANK_VALUE; i++) {
            this.basicDeck.add(new StandardCard(Suit.Clubs, Rank.intToRank(i)));
            this.basicDeck.add(new StandardCard(Suit.Diamonds, Rank.intToRank(i)));
            this.basicDeck.add(new StandardCard(Suit.Hearts, Rank.intToRank(i)));
            this.basicDeck.add(new StandardCard(Suit.Spades, Rank.intToRank(i)));
        }

        this.out = new StringBuffer();
        this.outInvalidKey = new StringBuffer();
        this.outInvalidNumber = new StringBuffer();
        this.outQuit = new StringBuffer();


        this.model1 = new WhistModel();
        this.basicModel = new WhistModel(basicDeck, 4);

        this.controller1 = new WhistController(new StringReader("1"), out);
        this.controllerInvalidNumber = new WhistController(new StringReader("89"), this.outInvalidNumber);
        this.controllerInvalidKey = new WhistController(new StringReader("j"), this.outInvalidKey);
        this.controllerQuit = new WhistController(new StringReader("q"), this.outQuit);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayExceptions() {
        this.initData();
        controller1.playGame(nullModel, 4);
        controller1.playGame(model1, 0);
        controller1.playGame(model1, -1);
    }

    @Test
    public void testInvalidInput() {
        this.initData();
        assertEquals("Try again. Invalid input\n", outInvalidKey.toString());
        assertEquals("Try again. Invalid input."+" Please enter a valid card number.\n", outInvalidNumber.toString());
    }

    @Test
    public void testPlay() {
        this.initData();
        System.out.print(out.toString());
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                basicModel.play(j, 0);
            }
        }
        System.out.print(out.toString());
    }

    @Test
    public void testPlayQuit() {
        this.initData();
        assertEquals("Game quit prematurely.", this.outQuit.toString());
    }
}
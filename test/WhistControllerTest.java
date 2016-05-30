
import cs3500.hw02.*;
import cs3500.hw03.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by baharsheikhi on 5/28/16.
 * To test the whist controller class
 */
public class WhistControllerTest {

    List<StandardCard> basicDeck;

    StringBuffer outNull;
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


    /**
     * The initial conditions
     */
    private void initData() {

        this.basicDeck = new ArrayList<StandardCard>();

        for (int i = StandardCard.MIN_RANK_VALUE;
             i <= StandardCard.MAX_RANK_VALUE; i++) {
            this.basicDeck.add
                    (new StandardCard(Suit.Clubs, Rank.intToRank(i)));
            this.basicDeck.add
                    (new StandardCard(Suit.Diamonds, Rank.intToRank(i)));
            this.basicDeck.add
                    (new StandardCard(Suit.Hearts, Rank.intToRank(i)));
            this.basicDeck.add
                    (new StandardCard(Suit.Spades, Rank.intToRank(i)));
        }

        this.out = new StringBuffer();
        this.outNull = new StringBuffer();
        this.outInvalidKey = new StringBuffer();
        this.outInvalidNumber = new StringBuffer();
        this.outQuit = new StringBuffer();

        this.model1 = new WhistModel();
        this.basicModel = new WhistModel(basicDeck, 4);

        this.controller1 = new WhistController(new StringReader("1"),
                outNull);
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
        new WhistController(new StringReader("j"),
                outInvalidKey).playGame(new WhistModel(this.basicDeck), 4);
        new WhistController(new StringReader("89"),
                outInvalidNumber).playGame(new WhistModel(this.basicDeck), 4);
        assertTrue(outInvalidKey.toString().contains
                ("Try again. Invalid input"));
        assertTrue(outInvalidNumber.toString().contains
                ("Try again. Invalid input." + " Please enter a valid card number.\n"));
    }

    @Test
    public void testPlay() {
        this.initData();
        new WhistController(new StringReader("1"), out).playGame(basicModel, 4);
        String[] spliced = out.toString().split("Turn");

        assertTrue(spliced[0].contains("Player 1:"));
        assertTrue(spliced[0].contains("Player 2:"));
        assertTrue(spliced[0].contains("Player 3:"));
        assertTrue(spliced[0].contains("Player 4:"));
        assertTrue(spliced[0].contains("Player 1 score: 0\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n"));
        assertTrue(spliced[1].contains("Player 1:"));
        assertTrue(spliced[1].contains("Player 2:"));
        assertTrue(spliced[1].contains("Player 3:"));
        assertTrue(spliced[1].contains("Player 4:"));
        assertTrue(spliced[1].contains("Player 1 score: 0\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n"));
        assertFalse(spliced[0].contains("Game quit prematurely."));
        assertFalse(spliced[1].contains("Game quit prematurely."));
        assertFalse(spliced[0].contains("Try again. Invalid input"));
        assertFalse(spliced[1].contains("Try again. Invalid input"));

    }

    @Test
    public void testPlayQuit() {
        this.initData();
        new WhistController(new StringReader("q"),
                outQuit).playGame(new WhistModel(this.basicDeck), 4);

        String result = outQuit.toString().substring
                (outQuit.toString().indexOf('G'));

        assertEquals("Game quit prematurely.", result);
    }

}


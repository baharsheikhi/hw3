import org.junit.Test;

import cs3500.hw02.Rank;
import cs3500.hw02.StandardCard;
import cs3500.hw02.Suit;
import cs3500.hw03.WhistModel;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Created by bahar on 5/23/16.
 * to test the whistModel class
 */
public class WhistModelTest {

    //imagine this is a game with 4 players and each player has 13 cards in their hands
    List<StandardCard> player1Wins;
    WhistModel model1;
    WhistModel modelSkip;


    public void initData() {
        this.player1Wins = new ArrayList<>();
        //player 1 should win this round because it has the highest rank
        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Ace));
        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.King));
        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Queen));
        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Jack));

        //Player 1 should win this round because it has the correct suit
        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Ten));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Ten));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Ten));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Ten));

        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Ace));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.King));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Queen));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Jack));

        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Ace));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.King));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Queen));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Jack));

        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Ace));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.King));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Queen));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Jack));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Nine));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Nine));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Nine));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Nine));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Eight));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Eight));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Eight));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Eight));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Seven));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Seven));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Seven));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Seven));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Six));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Six));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Six));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Six));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Five));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Five));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Five));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Five));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Four));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Four));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Four));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Four));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Three));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Three));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Three));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Three));

        this.player1Wins.add(new StandardCard(Suit.Clubs, Rank.Two));
        this.player1Wins.add(new StandardCard(Suit.Diamonds, Rank.Two));
        this.player1Wins.add(new StandardCard(Suit.Hearts, Rank.Two));
        this.player1Wins.add(new StandardCard(Suit.Spades, Rank.Two));

        this.model1 = new WhistModel(this.player1Wins, 4);

    }

    @Test(expected = IllegalArgumentException.class)
    public void playExceptions() {
    }

    private String[] getPlayerHand(int playerNo, WhistModel model) {
        this.initData();

        String[] modelParts = model.getGameState().split("Player");

        String[] cardsUntrimmed = modelParts[playerNo].split(":");

        return cardsUntrimmed[1].split(",");
    }

    private int playerScore(int playerNo, WhistModel model, int modelPlayerSize) {

        if (playerNo > modelPlayerSize || playerNo < 1) {
            throw new IllegalArgumentException("illegal player number");
        }
        String[] modelParts = model.getGameState().split("Player");

        String lineScore = modelParts[modelPlayerSize + playerNo];

        String[] scoreAsArray = lineScore.split(":");

        String score = scoreAsArray[1];

        if (score.charAt(2) != '\n') {
            return Integer.parseInt(Character.toString(score.charAt(1)) + Character.toString(score.charAt(2)));
        } else {
            return Integer.parseInt(Character.toString(score.charAt(1)));
        }
    }

    private int getWinner(WhistModel model, int modelPlayerSize) {
        if (!model.isGameOver()) {
            throw new IllegalArgumentException("Game is not over and cannot get winner");
        }

        String[] modelParts = model.getGameState().split("Player");

        String lineWinner = modelParts[(modelPlayerSize * 2) + 1];

        return Integer.parseInt(Character.toString(lineWinner.charAt(1)));
    }


    @Test
    public void testPlay() {
        this.initData();
        //testing the initial condition
        assertEquals(13, this.getPlayerHand(1, model1).length);
        assertEquals(13, this.getPlayerHand(2, model1).length);
        assertEquals(13, this.getPlayerHand(3, model1).length);
        assertEquals(13, this.getPlayerHand(4, model1).length);
        assertEquals(0, this.playerScore(1, model1, 4));
        assertEquals(0, this.playerScore(2, model1, 4));
        assertEquals(0, this.playerScore(3, model1, 4));
        assertEquals(0, this.playerScore(4, model1, 4));
        assertEquals(0, model1.getCurrentPlayer());

        model1.play(0, 0);

        //something was removed
        assertEquals(12, this.getPlayerHand(1, model1).length);
        //everything else is the same
        assertEquals(13, this.getPlayerHand(2, model1).length);
        assertEquals(13, this.getPlayerHand(3, model1).length);
        assertEquals(13, this.getPlayerHand(4, model1).length);
        assertEquals(0, this.playerScore(1, model1, 4));
        assertEquals(0, this.playerScore(2, model1, 4));
        assertEquals(0, this.playerScore(3, model1, 4));
        assertEquals(0, this.playerScore(4, model1, 4));
        assertEquals(1, model1.getCurrentPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCurrentPlayerExceptions() {
    }

    @Test
    public void simpleGameTest() {
        this.initData();

        assertFalse(model1.isGameOver());
        //should always start with 0
        assertEquals(0, model1.getCurrentPlayer());
        //the first player moves : 12 cards left
        model1.play(0, 0);
        assertFalse(model1.isGameOver());
        //now it is the second player's turn
        assertEquals(1, model1.getCurrentPlayer());
        //the second player moves: 12 cards left
        model1.play(1, 0);
        assertFalse(model1.isGameOver());
        //now it is the third player's turn
        assertEquals(2, model1.getCurrentPlayer());
        //the third player moves: 13 cards left
        model1.play(2, 0);
        assertFalse(model1.isGameOver());
        //now it is the fourth player's turn
        assertEquals(3, model1.getCurrentPlayer());
        //the fourth player moves
        model1.play(3, 0);
        assertFalse(model1.isGameOver());
        assertEquals(1, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));
        //IMPORTANT: NOW IT IS THE FIRST PLAYER'S TURN AGAIN
        assertEquals(0, model1.getCurrentPlayer());
        assertFalse(model1.isGameOver());
        model1.play(0, 0); // 11 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(2, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //10 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(3, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //9 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(4, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //8 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(5, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //7 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(6, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //6 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(7, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //5 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(8, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //4 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(9, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //3 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(10, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //2 cards left
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);
        assertEquals(11, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));

        assertFalse(model1.isGameOver());
        model1.play(0, 0); //1 card left
        //throws an exception because it is not model1's turn
        try {
            model1.play(0, 0);
        } catch (IllegalArgumentException e) {
            this.playExceptions();
        }
        assertFalse(model1.isGameOver());
        model1.play(1, 0); //1 card left
        assertFalse(model1.isGameOver());
        model1.play(2, 0); //1 card left
        assertFalse(model1.isGameOver());
        model1.play(3, 0);
        assertEquals(12, playerScore(1, model1, 4));
        assertEquals(0, playerScore(2, model1, 4));
        assertEquals(0, playerScore(3, model1, 4));
        assertEquals(0, playerScore(4, model1, 4));
        assertFalse(model1.isGameOver());
        model1.play(0, 0);
        assertFalse(model1.isGameOver());
        model1.play(1, 0);
        assertFalse(model1.isGameOver());
        model1.play(2, 0);
        assertTrue(model1.isGameOver());
        assertEquals(0, this.getWinner(model1, 4));

        //should throw an exception if you're trying to move once the game is over
        try {
            model1.play(0, 0);
        } catch (IllegalArgumentException e) {
            this.playExceptions();
        }

        //throws an exception because the game is over
        try {
            model1.getCurrentPlayer();
        } catch (IllegalArgumentException e) {
            this.getCurrentPlayerExceptions();
        }
    }

    @Test
    public void testSkipPlayerPlay() {
        List<StandardCard> shortList = new ArrayList<StandardCard>();
        //X
        shortList.add(new StandardCard(Suit.Hearts, Rank.Two));
        //Y
        shortList.add(new StandardCard(Suit.Hearts, Rank.Three));
        //Z
        shortList.add(new StandardCard(Suit.Hearts, Rank.Five));
        //A
        shortList.add(new StandardCard(Suit.Spades, Rank.Two));
        //B
        shortList.add(new StandardCard(Suit.Spades, Rank.Three));
        //C
        shortList.add(new StandardCard(Suit.Spades, Rank.Five));
        //D
        shortList.add(new StandardCard(Suit.Diamonds, Rank.Two));
        //E
        shortList.add(new StandardCard(Suit.Diamonds, Rank.Ace));
        //F
        shortList.add(new StandardCard(Suit.Diamonds, Rank.Three));
        //G
        shortList.add(new StandardCard(Suit.Clubs, Rank.Two));
        //H
        shortList.add(new StandardCard(Suit.Clubs, Rank.Ace));

        try {
            this.modelSkip = new WhistModel(shortList, 3);
        } catch (IllegalArgumentException e) {
            modelSkip.play(0, 2);
            modelSkip.play(0, 0);
            modelSkip.play(0, 1);
            //player 3 should win here
            modelSkip.play(0, 2);
            modelSkip.play(0, 0);
            modelSkip.play(0, 1);
            //player 3 should win again
            modelSkip.play(0, 2);
            modelSkip.play(0, 0);
            modelSkip.play(0, 1);
            //player 2 should win here
            //because player 1 does not have any more cards
            modelSkip.play(0, 1);
            try {
                modelSkip.play(0, 0);
            } catch (IllegalArgumentException f) {
                this.playExceptions();
            }
            modelSkip.play(0, 2);
            assertTrue(modelSkip.isGameOver());
        }
    }

    @Test
    public void testGameState() {
        this.initData();
        assertEquals("Number of players: 4\n" +
                "Player 1: A♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♦, A♥, A♠\n" +
                "Player 2: K♣, K♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, K♥, K♠\n" +
                "Player 3: Q♣, Q♦, Q♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, Q♠\n" +
                "Player 4: J♣, J♦, J♥, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
                "Player 1 score: 0\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n" +
                "Turn: Player 1", this.model1.getGameState());

        model1.play(0, 0); //13 cards

        assertEquals("Number of players: 4\n" +
                "Player 1: 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♦, A♥, A♠\n" +
                "Player 2: K♣, K♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, K♥, K♠\n" +
                "Player 3: Q♣, Q♦, Q♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, Q♠\n" +
                "Player 4: J♣, J♦, J♥, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
                "Player 1 score: 0\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n" +
                "Turn: Player 2", this.model1.getGameState());

        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0); //12 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0); //11 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0); //10 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0); // 9 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0); //8 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0);//7 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0);//6 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0);//5 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0);//4 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0);//3 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0);//2 cards
        model1.play(1, 0);
        model1.play(2, 0);
        model1.play(3, 0);

        model1.play(0, 0);//1 card
        model1.play(1, 0);
        model1.play(2, 0);

        assertEquals("Number of players: 4\n" +
                "Player 1: \n" +
                "Player 2: \n" +
                "Player 3: \n" +
                "Player 4: 2♠\n" +
                "Player 1 score: 12\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n" +
                "Game over. Player 1 won.", this.model1.getGameState());

    }

}
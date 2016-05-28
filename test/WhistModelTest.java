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

    private List<StandardCard> player1Wins;
    private WhistModel model1;
    private WhistModel modelSkip;
    private WhistModel manyPlayers;
    private WhistModel tie;
    private List<StandardCard> tieDeck;
    private WhistModel gameOverAtStart;
    private WhistModel gameAlmostOverAtStart;
    List<StandardCard> basicDeck;

    /**
     * Initializes the data of this tester class
     */
    public void initData() {
        this.player1Wins = new ArrayList<>();
        this.tieDeck = new ArrayList<>();

        List<StandardCard> basicDeck = new ArrayList<StandardCard>();

        for (int i = StandardCard.MIN_RANK_VALUE; i <= StandardCard.MAX_RANK_VALUE; i++) {
            basicDeck.add(new StandardCard(Suit.Clubs, Rank.intToRank(i)));
            basicDeck.add(new StandardCard(Suit.Diamonds, Rank.intToRank(i)));
            basicDeck.add(new StandardCard(Suit.Hearts, Rank.intToRank(i)));
            basicDeck.add(new StandardCard(Suit.Spades, Rank.intToRank(i)));
        }

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

        //player 1 wins
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Ace));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.King));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Queen));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Jack));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Ten));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Nine));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Eight));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Seven));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Six));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Five));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Four));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Three));
        this.tieDeck.add(new StandardCard(Suit.Clubs, Rank.Two));

        //player 2 wins
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.King));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Ace));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Queen));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Jack));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Ten));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Nine));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Eight));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Seven));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Six));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Five));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Four));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Three));
        this.tieDeck.add(new StandardCard(Suit.Diamonds, Rank.Two));

        //player 3 wins
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.King));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Queen));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Ace));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Jack));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Ten));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Nine));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Eight));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Seven));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Six));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Five));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Four));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Three));
        this.tieDeck.add(new StandardCard(Suit.Hearts, Rank.Two));

        //player 4 wins
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.King));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Queen));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Jack));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Ace));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Ten));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Nine));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Eight));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Seven));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Six));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Five));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Four));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Three));
        this.tieDeck.add(new StandardCard(Suit.Spades, Rank.Two));


        this.model1 = new WhistModel(this.player1Wins, 4);
        this.manyPlayers = new WhistModel(this.player1Wins, 52);
        this.tie = new WhistModel(this.tieDeck, 13);
        this.gameOverAtStart = new WhistModel(basicDeck, 3);
        this.gameAlmostOverAtStart = new WhistModel(basicDeck, 5);
    }


    /**
     * Gets the given player's hand given a model
     * @param playerNo the player to get the hand from
     * @param model the model to get the player's hand
     * @return the player's hand
     */
    private String[] getPlayerHand(int playerNo, WhistModel model) {
        this.initData();

        String[] modelParts = model.getGameState().split("Player");

        String[] cardsUntrimmed = modelParts[playerNo].split(":");

        return cardsUntrimmed[1].split(",");
    }

    /**
     * Gets the given player's score given a model and the number of players
     * @param playerNo the player to get the score from
     * @param model the model to get it from
     * @param modelPlayerSize the number of player's in the model
     * @return the given player's score
     */
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

    /**
     * Gets the winner of the given game
     * @param model the model
     * @param modelPlayerSize the number of players
     * @return the winner
     */
    private int getWinner(WhistModel model, int modelPlayerSize) {
        if (!model.isGameOver()) {
            throw new IllegalArgumentException("Game is not over and cannot get winner");
        }

        String[] modelParts = model.getGameState().split("Player");

        String lineWinner = modelParts[(modelPlayerSize * 2) + 1];

        return Integer.parseInt(Character.toString(lineWinner.charAt(1)));
    }


    //FIXME
    @Test
    public void testPlay() {
        this.initData();
        //testing the initial condition
        assertEquals(1, this.getPlayerHand(1, manyPlayers).length);
        assertEquals(1, this.getPlayerHand(2, manyPlayers).length);
        assertEquals(1, this.getPlayerHand(3, manyPlayers).length);
        assertEquals(1, this.getPlayerHand(4, manyPlayers).length);
        assertEquals(0, this.playerScore(1, manyPlayers, 52));
        assertEquals(0, this.playerScore(2, manyPlayers, 52));
        assertEquals(0, this.playerScore(3, manyPlayers, 52));
        assertEquals(0, this.playerScore(4, manyPlayers, 52));
        assertEquals(0, manyPlayers.getCurrentPlayer());

        manyPlayers.play(0, 0);

        //something was removed
        assertEquals(1, this.getPlayerHand(1, manyPlayers).length);
        //everything else is the same
        assertEquals(1, this.getPlayerHand(2, manyPlayers).length);
        assertEquals(1, this.getPlayerHand(3, manyPlayers).length);
        assertEquals(1, this.getPlayerHand(4, manyPlayers).length);
        assertEquals(0, this.playerScore(1, manyPlayers, 52));
        assertEquals(0, this.playerScore(2, manyPlayers, 52));
        assertEquals(0, this.playerScore(3, manyPlayers, 52));
        assertEquals(0, this.playerScore(4, manyPlayers, 52));
        assertEquals(1, manyPlayers.getCurrentPlayer());
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
        model1.play(3, 0);
        assertTrue(model1.isGameOver());
        assertEquals(1, this.getWinner(model1, 4));
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

    //tests to see that the game ends at the appropriate time
    @Test
    public void playEndOfGameTest() {
        this.initData();

        for (int i = 0; i < 52; i++) {
            manyPlayers.play(i, 0);
        }

        assertEquals(true, manyPlayers.isGameOver());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayGameOverException() {
        this.initData();
        for (int i = 0; i < 52; i++) {
            manyPlayers.play(i, 0);
        }

        manyPlayers.play(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayOutOfTurnException() {
        this.initData();
        manyPlayers.play(0, 0);
        manyPlayers.play(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadInputPlay() {
        this.initData();
        this.manyPlayers.play(0, 3);
        this.manyPlayers.play(15, 37);
        this.manyPlayers.play(15, 0);
    }

    @Test
    public void testTie() {
        this.initData();

        tie.play(0, 0);
        tie.play(1, 0);
        tie.play(2, 0);
        tie.play(3, 0);
        tie.play(4, 0);
        tie.play(5, 0);
        tie.play(6, 0);
        tie.play(7, 0);
        tie.play(8, 0);
        tie.play(9, 0);
        tie.play(10, 0);
        tie.play(11, 0);
        tie.play(12, 0);

        tie.play(0, 0);
        tie.play(1, 0);
        tie.play(2, 0);
        tie.play(3, 0);
        tie.play(4, 0);
        tie.play(5, 0);
        tie.play(6, 0);
        tie.play(7, 0);
        tie.play(8, 0);
        tie.play(9, 0);
        tie.play(10, 0);
        tie.play(11, 0);
        tie.play(12, 0);

        tie.play(1, 0);
        tie.play(2, 0);
        tie.play(3, 0);
        tie.play(4, 0);
        tie.play(5, 0);
        tie.play(6, 0);
        tie.play(7, 0);
        tie.play(8, 0);
        tie.play(9, 0);
        tie.play(10, 0);
        tie.play(11, 0);
        tie.play(12, 0);
        tie.play(0, 0);

        tie.play(2, 0);
        tie.play(3, 0);
        tie.play(4, 0);
        tie.play(5, 0);
        tie.play(6, 0);
        tie.play(7, 0);
        tie.play(8, 0);
        tie.play(9, 0);
        tie.play(10, 0);
        tie.play(11, 0);
        tie.play(12, 0);
        tie.play(0, 0);
        tie.play(1, 0);

        assertEquals("Number of players: 13\n" +
                "Player 1: \n" +
                "Player 2: \n" +
                "Player 3: \n" +
                "Player 4: \n" +
                "Player 5: \n" +
                "Player 6: \n" +
                "Player 7: \n" +
                "Player 8: \n" +
                "Player 9: \n" +
                "Player 10: \n" +
                "Player 11: \n" +
                "Player 12: \n" +
                "Player 13: \n" +
                "Player 1 score: 1\n" +
                "Player 2 score: 1\n" +
                "Player 3 score: 1\n" +
                "Player 4 score: 1\n" +
                "Player 5 score: 0\n" +
                "Player 6 score: 0\n" +
                "Player 7 score: 0\n" +
                "Player 8 score: 0\n" +
                "Player 9 score: 0\n" +
                "Player 10 score: 0\n" +
                "Player 11 score: 0\n" +
                "Player 12 score: 0\n" +
                "Player 13 score: 0\n" +
                "Game over. Player 1, 2, 3, 4 won.", this.tie.getGameState());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsGameOverException() {
        model1 = new WhistModel();
        model1.isGameOver();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayBeforePlayingException() {
        model1 = new WhistModel();
        model1.isGameOver();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrentPlayerExceptionAfter() {
        this.initData();
        for (int i = 0; i < 52; i++) {
            manyPlayers.play(i, 0);
        }

        manyPlayers.getCurrentPlayer();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrentPlayerExceptionBefore() {
        this.model1 = new WhistModel();
        model1.getCurrentPlayer();
    }

    @Test
    public void testPlayIsGameOver() {
        this.initData();

        for (int j = 0; j < 17; j++) {
            for (int i = 0; i < 3; i++) {
                this.gameOverAtStart.play(i, 0);
            }
        }

        assertFalse(this.gameOverAtStart.isGameOver());
        this.gameOverAtStart.play(0, 0);
        assertTrue(this.gameOverAtStart.isGameOver());
    }

    @Test
    public void testPlayGameIsntOver() {
        this.initData();

        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);
        this.gameAlmostOverAtStart.play(4, 0);


        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);

        this.gameAlmostOverAtStart.play(4, 0);
        this.gameAlmostOverAtStart.play(0, 0);
        this.gameAlmostOverAtStart.play(1, 0);
        this.gameAlmostOverAtStart.play(2, 0);
        this.gameAlmostOverAtStart.play(3, 0);


        assertFalse(gameAlmostOverAtStart.isGameOver());
        assertEquals(0, this.gameAlmostOverAtStart.getCurrentPlayer());

        this.gameAlmostOverAtStart.play(0, 0);

        assertFalse(gameAlmostOverAtStart.isGameOver());

        this.gameAlmostOverAtStart.play(1, 0);
        assertEquals("Number of players: 5\n" +
                "Player 1: \n" +
                "Player 2: \n" +
                "Player 3: \n" +
                "Player 4: \n" +
                "Player 5: \n" +
                "Player 1 score: 0\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n" +
                "Player 5 score: 10\n" +
                "Game over. Player 5 won.", this.gameAlmostOverAtStart.getGameState());

    }

}
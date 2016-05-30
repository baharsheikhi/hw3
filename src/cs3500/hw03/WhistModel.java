package cs3500.hw03;

import java.util.*;

import cs3500.hw02.*;

/**
 * Created by bahar on 5/23/16.
 * To represent the model of the whist game.
 */
public class WhistModel extends GenericStandardDeckGame implements CardGameModel<StandardCard> {

    @Override
    public void play(int playerNo, int cardIdx) {
        if (playerNo >= this.getPlayers().size()) {
            throw
                    new IllegalArgumentException("Please enter a valid player number");
        }

        if (cardIdx >= this.getPlayers().get(playerNo).cardCount()) {
            throw
                    new IllegalArgumentException("Please enter a valid card index");
        }

        if (playerNo != this.whoseTurn) {
            throw
                    new IllegalArgumentException("Not this player's turn to play");
        }

        if (this.getPlayers().size() == 0) {
            throw
                    new IllegalArgumentException("Cannot move before the game has started");
        }


        StandardCard played =
                this.getPlayers().get(playerNo).removeCard(cardIdx, this.trickSuit);


        //if its the last play then determine the winner, update the score of the winner
        //and then set whoever's turn it is to the winner
        if (playerNo == (Math.floorMod(this.currentWinner - 1, this.getPlayers().size()))) {
            cardsInPlay.put(played, playerNo);
            this.setCurrentWinner(this.getWinner(this.getWinningList()));
            this.getPlayers().get(currentWinner).updateScore();
            if (this.getPlayers().get(currentWinner).cardCount() != 0) {
                this.setWhoseTurn(currentWinner);
                this.isTrickOver = true;
            } else if (!this.isGameOver()) {
                this.updateTurn();
            }
        }

        //if its the first play then set the suit
        else if (playerNo == this.currentWinner) {
            if (this.isGameOver()) {
                throw new
                        IllegalArgumentException("Can't move once the game is over");
            }
            this.setTrickSuit(Suit.getSuit(played.toString()), playerNo);
            this.cardsInPlay = new HashMap<StandardCard, Integer>();
            cardsInPlay.put(played, playerNo);
            this.updateTurn();
            this.isTrickOver = false;

        } else {
            cardsInPlay.put(played, playerNo);
            this.updateTurn();
        }

    }

    @Override
    public int getCurrentPlayer() {
        if (this.getPlayers().size() == 0) {
            throw new
                    IllegalArgumentException("The game has not started yet");
        }
        if (this.isGameOver()) {
            throw new
                    IllegalArgumentException("The game is over and cannot get the current player");
        }
        return this.whoseTurn;
    }

    @Override
    public boolean isGameOver() {
        if (this.getPlayers().size() == 0) {
            throw new IllegalArgumentException("Game hasn't started");
        }
        int countHasCards = 0;
        for (Player p : this.getPlayers()) {
            if (p.cardCount() != 0) {
                countHasCards += 1;
            }
        }
        return (countHasCards < 2) && (this.isTrickOver == true);
    }

    @Override
    public String getGameState() {
        String start = super.getGameState();

        for (int i = 0; i < this.getPlayers().size(); i++) {
            if (i == this.getPlayers().size() - 1) {
                start += "\nPlayer " + (i + 1) + " score: " + getPlayers().get(i).getScore();
            } else {
                start += "\nPlayer " + (i + 1) + " score: " + getPlayers().get(i).getScore();
            }
        }

        if (this.isGameOver()) {
            String winners = "";
            for (int i = 0; i < this.getFinalWinners().size(); i++) {
                if (i == this.getFinalWinners().size() - 1) {
                    winners += (this.getFinalWinners().get(i) + 1);
                } else {
                    winners += (this.getFinalWinners().get(i) + 1) + ", ";
                }
            }
            start += "\nGame over. Player " + winners + " won.";
        } else {
            start += "\nTurn: Player " + (this.whoseTurn + 1);
        }
        return start;
    }

    //constructor is the same as the default without parameters
    //the deck becomes getDeck //NOT SHUFFLED

    /**
     * Creates a WhistModel with the deck as a shuffled one
     * It is the first player's turn.
     * There is no suit of the first trick.
     * The list of playe    rs is empty.
     * StartPlay must be called with a custom number of players to begin
     * a game with this constructor
     */
    public WhistModel() {
        super();
        this.whoseTurn = 0;
        this.currentWinner = 0;
        this.trickSuit = null;
        this.cardsInPlay = new HashMap<StandardCard, Integer>();
        this.isTrickOver = false;
    }

    /**
     * Creates a WhistModel with a custom deck and a custom
     * number of players.
     * It is the first player's turn.
     * There is no suit of the first trick.
     * The game is already started when this constructor is called.
     *
     * @param deck       the custom deck
     * @param numPlayers the number of players
     */
    public WhistModel(List<StandardCard> deck, int numPlayers) {
        super(deck, numPlayers);
        this.whoseTurn = 0;
        this.currentWinner = 0;
        this.trickSuit = null;
        this.cardsInPlay = new HashMap<StandardCard, Integer>();
        this.isTrickOver = false;
    }

    /**
     * Creates a WhistModel with a custom number of players.
     * The deck is shuffled.
     * It is the first player's turn.
     * There is no suit of the first trick.
     * The game is already started when this constructor is called.
     *
     * @param numPlayers the number of players
     */
    public WhistModel(int numPlayers) {
        super(numPlayers);
        this.whoseTurn = 0;
        this.currentWinner = 0;
        this.trickSuit = null;
        this.cardsInPlay = new HashMap<StandardCard, Integer>();
        this.isTrickOver = false;
    }

    /**
     * Creates a WhistModel with a custom deck and a custom
     * number of players.
     * It is the first player's turn.
     * There is no suit of the first trick.
     * The game is already started when this constructor is called.
     *
     * @param deck the custom deck
     */
    public WhistModel(List<StandardCard> deck) {
        super(deck);
        this.whoseTurn = 0;
        this.currentWinner = 0;
        this.trickSuit = null;
        this.cardsInPlay = new HashMap<StandardCard, Integer>();
        this.isTrickOver = false;
    }

    private int whoseTurn;
    private Suit trickSuit;
    private int currentWinner;
    private HashMap<StandardCard, Integer> cardsInPlay;
    private boolean isTrickOver;


    /**
     * Gets the final winners of this game in a list
     *
     * @return the list of the final winners of this game
     */
    private List<Integer> getFinalWinners() {
        if (!this.isGameOver()) {
            throw new IllegalArgumentException("Cannot determine winners before the game is over");
        }
        List<Integer> winners = new ArrayList<>();
        List<Integer> winningScores = new ArrayList<>();
        Map<Integer, Integer> playerToScore = new HashMap<>(); //Player to Score
        for (int i = 0; i < this.getPlayers().size(); i++) {
            winningScores.add(this.getPlayers().get(i).getScore());
            playerToScore.put(i, this.getPlayers().get(i).getScore());
        }

        Comparator reverse = Collections.reverseOrder();
        Collections.sort(winningScores, reverse);
        //winningScores.get(0) is the highest score --> how do I find out which player had it?
        for (Integer i :
                playerToScore.keySet()) {
            if (playerToScore.get(i) == winningScores.get(0)) {
                winners.add(i);
            }
        }
        return winners;
    }

    /**
     * Increments the turn of the player it is;
     * wraps around to the 0th player
     * if it reaches the highest player
     */
    private void incrementWhoseTurn() {

        if (this.whoseTurn == this.getPlayers().size() - 1) {
            this.whoseTurn = 0;
        } else {
            whoseTurn += 1;
        }
    }

    /**
     * update's the turn appropriately, taking into
     * consideration if the next player's has cards.
     * This method stops updating the turns when it
     * reaches the last player of a trick.
     */
    private void updateTurn() {
        if (this.whoseTurn == this.currentWinner) {
            this.isTrickOver = false;
        }
        if (this.getPlayers().get(Math.floorMod((whoseTurn + 1),
                this.getPlayers().size())).cardCount() != 0) {
            this.incrementWhoseTurn();
        } else {
            this.incrementWhoseTurn();
            if (this.whoseTurn == (Math.floorMod(this.currentWinner - 1,
                    this.getPlayers().size()))) {
                this.isTrickOver = true;
            } else {
                this.updateTurn();
            }
        }
    }


    /**
     * Gets the winner of this trick
     *
     * @return the player with the highest card
     */
    private int getWinner(List<StandardCard> cards) {
        int ret;
        if (Suit.getSuit(cards.get(0).toString()) == this.trickSuit) {
            ret = cardsInPlay.get(cards.get(0));
        } else {
            cards.remove(0);
            ret = this.getWinner(cards);

        }
        return ret;
    }

    /**
     * Gets the cards played in this trick.
     *
     * @return the list of cards played in this trick.
     */
    private List<StandardCard> getWinningList() {
        Set<StandardCard> cardSet = this.cardsInPlay.keySet();
        List<StandardCard> cards = new ArrayList<>(cardSet);
        Collections.sort(cards);
        return cards;
    }


    /**
     * sets whoever's turn it is to the given int
     *
     * @param i the new player's whose turn it is
     */
    private void setWhoseTurn(int i) {
        if (i >= this.getPlayers().size()) {
            throw new IllegalArgumentException("Please enter a valid player");
        }
        this.whoseTurn = i;
    }

    /**
     * sets the winning suit of this trick to the given suit
     *
     * @param s the new suit of this trick
     */
    private void setTrickSuit(Suit s, int player) {
        if (player != this.currentWinner) {
            throw
                    new IllegalArgumentException
                            ("Only the first player of this trick can set the suit");
        }
        this.trickSuit = s;
    }

    /**
     * sets the winner of this trick to the given player
     *
     * @param i the winner of this round
     */
    private void setCurrentWinner(int i) {
        if (i >= this.getPlayers().size()) {
            throw new IllegalArgumentException("Please enter a valid player");
        }
        this.currentWinner = i;
    }

}





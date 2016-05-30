package cs3500.hw03;

import cs3500.hw02.GenericCardGameModel;

/**
 * Created by bahar on 5/23/16.
 * An interface to represent card games
 */
public interface CardGameModel<K> extends GenericCardGameModel<K> {

    /**
     * plays the card at index cardIdx in the set of cards for player number playerNo.
     * It is assumed that both player numbers and card indices begin with 0.
     * It is further assumed that players’ hands are sorted clubs, diamonds, hearts, spades
     * with decreasing ranks starting with ace
     *
     * @param playerNo the player number
     * @param cardIdx  the card of the player to play
     */
    void play(int playerNo, int cardIdx);


    /**
     * @return the player whose turn it is to play (again, player numbers begin with 0)
     * It should throw an IllegalArgumentException if the game has ended.
     */
    int getCurrentPlayer();

    /**
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();
}

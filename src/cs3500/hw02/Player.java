package cs3500.hw02; /**
 * Created by baharsheikhi on 5/18/16.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Changes made for homework 3:
 * added a score field to keep track of the player's score
 * added a public method that counted the number of cards in this player's hand
 * added 2 methods that removed cards: one that just removed the card at the given index and
 * one that checked to see if there were no other cards of the given suit for Whist.
 * Added a public getter method for the score.
 * Added a method to update this player's score by one.
 * <p>
 * to represent a player that plays a standard card game
 */
public class Player {
    /**
     * adds a card to this player's hand
     *
     * @param c the card to be added
     */
    public void addCard(StandardCard c) {
        this.hand.add(c);

    }

    /**
     * counts the number of cards in this player's hand
     *
     * @return the size of the player's hand (remember the last index is one minus this number)
     */
    public int cardCount() {
        return this.hand.size();
    }

    /**
     * removes a card from this player's hand
     *
     * @param idx the index of the card to be removed
     * @return the card that is removed
     */
    public StandardCard removeCard(int idx) {
        if (idx >= this.cardCount()) {
            throw new IllegalArgumentException("Please enter a valid index");
        }
        return this.hand.remove(idx);
    }

    @Override
    public String toString() {
        String str = "";
        Collections.sort(this.hand);
        for (int i = 0; i < this.hand.size(); i++) {
            if (i == this.hand.size() - 1) {
                str += this.hand.get(i).toString();
            } else {
                str += this.hand.get(i).toString() + ", ";
            }
        }

        return str;
    }

    private final List<StandardCard> hand;
    private int score;

    /**
     * creates a standard player with an empty list of cards
     */
    public Player() {
        this.hand = new ArrayList<StandardCard>();
        this.score = 0;
    }

    /**
     * Update's this player's score
     */
    public void updateScore() {
        score += 1;
    }

    /**
     * Gets this player's score
     *
     * @return this player's score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * If the card at the given index is not of the trickSuit,
     * checks to see if the player has a card of the trickSuit,
     * throws an exception if the player does have a card of the given trickSuit
     *
     * @param idx
     * @param trickSuit
     */
    public StandardCard removeCard(int idx, Suit trickSuit) {
        StandardCard ret = null;
        if (this.hand.get(idx).getSuit() != trickSuit) {
            boolean isCheating = false;
            for (StandardCard c : this.hand) {
                if (c.getSuit() == trickSuit) {
                    isCheating = true;
                    throw new IllegalArgumentException("Please choose a card of the correct Suit");
                }
            }
            if (isCheating == false) {
                ret = this.removeCard(idx);
            }
        } else {
            ret = this.removeCard(idx);
        }
        return ret;
    }


}



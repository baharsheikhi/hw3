package cs3500.hw02; /**
 * Created by baharsheikhi on 5/18/16.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
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
   * @return the size of the player's hand (remember the last index is one minus this number)
   */
  public int cardCount() {
    return this.hand.size();
  }

  /**
   * removes a card from this player's hand
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

  /**
   * creates a standard player with an empty list of cards
   */
  public Player() {
    this.hand = new ArrayList<StandardCard>();
  }


}

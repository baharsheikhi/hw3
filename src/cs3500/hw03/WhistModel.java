package cs3500.hw03;

import java.util.List;

import cs3500.hw02.GenericStandardDeckGame;
import cs3500.hw02.Player;
import cs3500.hw02.StandardCard;

/**
 * Created by bahar on 5/23/16.
 */
public class WhistModel extends GenericStandardDeckGame implements CardGameModel<StandardCard> {

  @Override
  //TODO If this is the first play in the current trick, this card will determine this trick’s suit.
  // If this is the last play in the current trick, it must determine who won the trick and
  // update the tally of won tricks for that player.
  //if the current card cannot be played (i.e. it is of the wrong suit even though the player has a
  // //card of the correct suit) or if the game is already over, the method should throw an
  // IllegalArgumentException.
  public void play(int playerNo, int cardIdx) {
    if (playerNo >= this.players.size()) {
      throw new IllegalArgumentException("Please enter a valid player number");
    }

    if (cardIdx >= this.players.get(playerNo).cardCount()) {
      throw new IllegalArgumentException("Please enter a valid card index");
    }

    this.players.get(playerNo).removeCard(cardIdx);
    this.updateTurn();
  }

  @Override
  public int getCurrentPlayer() {
    if (this.isGameOver()) {
      throw new IllegalArgumentException("The game is over and cannot get the current player");
    }
    return this.players.get(this.whoseTurn);
  }

  @Override
  public boolean isGameOver() {
    int countHasCards = 0;
    for (Player p: this.players) {
      if (p.cardCount() != 0) {
        countHasCards+=1;
      }
    }
    return countHasCards < 2;
  }

  @Override
  public String getGameState() {
    return null;
  }

  //constructor is the same as the default without parameters
  //the deck becomes getDeck
  //players becomes 4
  public WhistModel() {
    super();
    this.whoseTurn = 0;
  }

  //TODO FINAL????
  private int whoseTurn;

  /**
   * update which player's turn it is.
   * if the last player is playing, then it brings it back to the first player
   * what happens when the game is over? --> return an illegalArgumentException
   */
  protected void updateTurn() {
    if (this.isGameOver()) {
      throw new IllegalArgumentException("The game has ended and cannot update the turn");
    }

    if (this.whoseTurn == this.players.size() - 1) {
      this.whoseTurn = 0;
    }

    else {
      whoseTurn+=1;
    }
  }

}

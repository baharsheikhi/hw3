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
    if (playerNo >= this.getPlayers().size()) {
      throw new IllegalArgumentException("Please enter a valid player number");
    }

    if (cardIdx >= this.getPlayers().get(playerNo).cardCount()) {
      throw new IllegalArgumentException("Please enter a valid card index");
    }

    this.getPlayers().get(playerNo).removeCard(cardIdx);
    this.updateTurn();
  }

  @Override
  public int getCurrentPlayer() {
    if (this.isGameOver()) {
      throw new IllegalArgumentException("The game is over and cannot get the current player");
    }
    return this.whoseTurn;
  }

  @Override
  public boolean isGameOver() {
    int countHasCards = 0;
    for (Player p: this.getPlayers()) {
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

    if (this.whoseTurn == this.getPlayers().size() - 1) {
      this.whoseTurn = 0;
    }

    else {
      whoseTurn+=1;
    }
  }

}

//the game should be over before you start the game
//correct thing to do would be to throw an exception
// start playing with at least two players
//declare explicitly overriding with different exception
//whatever changes you make to HW02 then document it and remember why you made those changes
//technically better to extend, now change the model, better avoiding casting
//protected method where you are creating players objects and then in the whist model override so that instead of
//creating player objects it creates whistplayer object, only the model class knows what is in the player is whistplayer
//has all the methods and functionality of a regular player....this is the normal way
//getter and a setter? for this problem and make this protected so model can access it



//WHAT HAS CHANGED:
//I MADE GETTER AND SETTER METHODS IN THE GENERICSTANDARDCARDGAME class


//who wins the trick if it ends?

//TODO make it so that start play isnt called in the constructor

//TODO isGameOVer exception and play exception

//TODO not always first player who starts the game

//TODO check if a player has cards

//the game cannot end in the middle of a trick
//all changes -- I changed the player class and added two methods remove() and add()
//write in comments that changes


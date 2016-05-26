package cs3500.hw03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cs3500.hw02.GenericStandardDeckGame;
import cs3500.hw02.Player;
import cs3500.hw02.StandardCard;
import cs3500.hw02.Suit;

/**
 * Created by bahar on 5/23/16.
 */
public class WhistModel extends GenericStandardDeckGame implements CardGameModel<StandardCard> {

  @Override
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

    if (playerNo != this.whoseTurn) {
      throw new IllegalArgumentException("Not this player's turn to play");
    }

    StandardCard played = this.getPlayers().get(playerNo).removeCard(cardIdx);

    //if its the last play then determine the winner, update the score of the winner
    //and then set whoever's turn it is to the winner
    if (playerNo == ((this.currentWinner - 1) % this.getPlayers().size())) {
      cardsInPlay.put(played, playerNo);
      this.setCurrentWinner(this.getWinner());
      this.getPlayers().get(currentWinner).updateScore();
      this.setWhoseTurn(currentWinner);
    }

    //if its the first play then set the suit
    else if (playerNo == this.currentWinner) {
      this.setTrickSuit(Suit.getSuit(played.toString()), playerNo);
      this.cardsInPlay = new HashMap<StandardCard, Integer>();
      cardsInPlay.put(played, playerNo);
      this.updateTurn();

    }

    else {
      cardsInPlay.put(played, playerNo);
      this.updateTurn();
    }

    }

  @Override
  public int getCurrentPlayer() {
    if (this.isGameOver()) {
      throw new IllegalArgumentException("The game is over and cannot get the current player");
    }
    return this.whoseTurn;
  }

  //// FIXME: 5/24/16 
  @Override
  public boolean isGameOver() {
    int countHasCards = 0;
    for (Player p : this.getPlayers()) {
      if (p.cardCount() != 0) {
        countHasCards += 1;
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
    this.currentWinner = 0;
    this.trickSuit = null;
    this.cardsInPlay = new HashMap<StandardCard, Integer>();
  }

  public WhistModel(List<StandardCard> deck, int numPlayers) {
    super(deck, numPlayers);
    this.whoseTurn = 0;
    this.currentWinner = 0;
    this.trickSuit = null;
    this.cardsInPlay = new HashMap<StandardCard, Integer>();
  }

  //TODO FINAL????
  private int whoseTurn;
  private Suit trickSuit;
  private int currentWinner;
  private HashMap<StandardCard, Integer> cardsInPlay;

  /**
   * update which player's turn it is. if the last player is playing, then it brings it back to the
   * first player what happens when the game is over? --> return an illegalArgumentException
   */
  private void incrementWhoseTurn() {
    if (this.isGameOver()) {
      throw new IllegalArgumentException("The game has ended and cannot update the turn");
    }

    if (this.whoseTurn == this.getPlayers().size() - 1) {
      this.whoseTurn = 0;
    } else {
      whoseTurn += 1;
    }
  }

  /**
   * update's the turn appropriately, taking into consideration if the next player's has cards
   */
  private void updateTurn() {
    if (this.isGameOver()) {
      throw new IllegalArgumentException("The game has ended and cannot update the turn");
    }

    if (this.whoseTurn == this.getPlayers().size() - 1) {
      throw new IllegalArgumentException("The turn should not be updating");
    }

    if (this.getPlayers().get(whoseTurn + 1).cardCount() != 0) {
      this.incrementWhoseTurn();
    } else {
      int count = 1;
      while (this.getPlayers().get(whoseTurn + count).cardCount() == 0) {
        this.incrementWhoseTurn();
        count++;
      }
    }
  }

  /**
   *
   * @return the player with the highest card
   */
  private int getWinner() {
    Set<StandardCard> cardSet = this.cardsInPlay.keySet();
    List<StandardCard> cards = new ArrayList<>(cardSet);
    Collections.sort(cards);
    if (Suit.getSuit(cards.get(0).toString()) == this.trickSuit) {
      return cardsInPlay.get(cards.get(0));
    }
    else {
      cards.remove(0);
      return this.getWinner();
    }
  }


  /**
   * sets whoever's turn it is to the given int
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
   * @param s the new suit of this trick
   */
  private void setTrickSuit(Suit s, int player) {
    if (player!= this.currentWinner) {
      throw new IllegalArgumentException("Only the first player of this trick can set the suit");
    }
    this.trickSuit = s;
  }

  /**
   * sets the winner of this trick to the given player
   * @param i the winner of this round
   */
  private void setCurrentWinner(int i) {
    if (i >= this.getPlayers().size()) {
      throw new IllegalArgumentException("Please enter a valid player");
    }
    this.currentWinner = i;
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

//if it is the first player, then the trick becomes what they put down
//if this player is the

//change added getSuit in teh suit class


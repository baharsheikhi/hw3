package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bahar on 5/16/16. This is a class to represent a generic standard deck game. It is the
 * foundation of card games and is a general model that can be used for many card games.
 *
 * All these games have some common aspects. They can be played by many players, and they use an
 * entire deck of cards. The deck of cards are distributed among all the players, so that each
 * player has a subset of cards at any time in the game. Players give up their cards as the game
 * progresses, and the game ends when all players have run out of cards.
 *
 * NOTE: In this standard deck game ace is the highest value
 *
 * The cards it uses is the standard 52-card deck, without jokers. Ranks 2-Ace and Suits Club,
 * Diamonds, Hearts, Spades
 */
public class GenericStandardDeckGame implements GenericCardGameModel<StandardCard> {

  @Override
  public List<StandardCard> getDeck() {
    List<StandardCard> allCards = new ArrayList<StandardCard>();

    for (int i = StandardCard.MIN_RANK_VALUE; i <= StandardCard.MAX_RANK_VALUE; i++) {
      allCards.add(new StandardCard(Suit.Clubs, Rank.intToRank(i)));
      allCards.add(new StandardCard(Suit.Diamonds, Rank.intToRank(i)));
      allCards.add(new StandardCard(Suit.Hearts, Rank.intToRank(i)));
      allCards.add(new StandardCard(Suit.Spades, Rank.intToRank(i)));
    }

    return allCards;
  }

  @Override
  public void startPlay(int numPlayers, List<StandardCard> deck) {
    if (isDeckValid(deck) == false) {
      throw new IllegalArgumentException("Please enter a valid deck");
    }

    if (!players.isEmpty()) {
      throw new IllegalArgumentException("The game has already been started");
    } else {
      this.initPlayers(numPlayers);

      for (int i = 0; i < deck.size(); i += numPlayers) {
        int count = 0;
        for (Player p : this.players) {
          p.addCard(deck.get(i + count));
          count++;
        }
      }

    }
  }

  @Override
  public String getGameState() {
    String ret = "Number of players: " + this.players.size() + "\n";

    for (int i = 0; i < this.players.size(); i++) {
      if (i == this.players.size() - 1) {
        ret += "Player " + (i + 1) + ": " + players.get(i).toString();
      } else {
        ret += "Player " + (i + 1) + ": " + players.get(i).toString() + "\n";
      }
    }

    return ret;
  }


  private final List<StandardCard> deck;
  private final List<Player> players;

  /**
   * adds the given number of players to this game's list of players
   */
  protected void initPlayers(int numPlayers) {
    for (int i = 0; i < numPlayers; i++) {
      players.add(new Player());
    }
  }

  /**
   * @return true if the given deck of cards is valid
   */
  protected static boolean isDeckValid(List<StandardCard> deck) {
    String str = "";

    for (StandardCard s : deck) {
      str += s.toString();
    }

    int clubsCount = 0;
    int diamondsCount = 0;
    int heartsCount = 0;
    int spadesCount = 0;

    int twoCount = 0;
    int threeCount = 0;
    int fourCount = 0;
    int fiveCount = 0;
    int sixCount = 0;
    int sevenCount = 0;
    int eightCount = 0;
    int nineCount = 0;
    int tenCount = 0;
    int jackCount = 0;
    int queenCount = 0;
    int kingCount = 0;
    int aceCount = 0;

    for (int i = 0; i < str.length(); i++) {
      switch (str.charAt(i)) {
        case '♣':
          clubsCount++;
          break;
        case '♦':
          diamondsCount++;
          break;
        case '♥':
          heartsCount++;
          break;
        case '♠':
          spadesCount++;
          break;
        case '2':
          twoCount++;
          break;
        case '3':
          threeCount++;
          break;
        case '4':
          fourCount++;
          break;
        case '5':
          fiveCount++;
          break;
        case '6':
          sixCount++;
          break;
        case '7':
          sevenCount++;
          break;
        case '8':
          eightCount++;
          break;
        case '9':
          nineCount++;
          break;
        case '1':
          tenCount++;
          break;
        case 'J':
          jackCount++;
          break;
        case 'Q':
          queenCount++;
          break;
        case 'K':
          kingCount++;
          break;
        case 'A':
          aceCount++;
          break;
        default:
      }
    }
    return clubsCount == 13 && diamondsCount == 13 && spadesCount == 13 && heartsCount == 13 &&
            twoCount == 4 && threeCount == 4 && fourCount == 4 && fiveCount == 4 && sixCount == 4 &&
            sevenCount == 4 && eightCount == 4 && nineCount == 4 && tenCount == 4 && jackCount == 4
            && queenCount == 4 && kingCount == 4 && aceCount == 4;
  }

  /**
   *Gets the players of this generic standard deck game
   * @return the list of players of this class
     */
  protected List<Player> getPlayers() {
    return this.players;
  }

  /**
   * Gets the deck of this generic standard deck game
   * @return the deck of this game
     */
  protected List<StandardCard> getCards() {
    return this.deck;
  }


  /**
   * creates a generic standard deck game with a given number of players. gets the deck and then
   * shuffles it. Distributes the cards among the players.
   *
   * @param numPlayers the number of players in this game throws an illegal argument exception if
   *                   the number of players is less than 1
   */
  public GenericStandardDeckGame(int numPlayers) {
    if (numPlayers < 1) {
      throw new IllegalArgumentException("Please enter a valid number of players");
    } else {
      this.deck = this.getDeck();
      Collections.shuffle(this.deck);
      this.players = new ArrayList<Player>();
      this.startPlay(numPlayers, this.deck);
    }
  }

  /**
   * creates a generic standard deck game with a shuffled deck and 4 players
   */
  public GenericStandardDeckGame() {
    this.deck = this.getDeck();
    Collections.shuffle(this.deck);
    this.players = new ArrayList<Player>();
    this.startPlay(4, this.deck);
  }

  /**
   * creates a generic standard deck game with the given deck, unshuffled
   *
   * @param deck the deck that will be the deck of the game
   */
  public GenericStandardDeckGame(List<StandardCard> deck) {
    if (isDeckValid(deck) == false) {
      throw new IllegalArgumentException("Please enter a valid deck");
    }
    this.deck = deck;
    this.players = new ArrayList<Player>();
    this.startPlay(4, deck);
  }

  public GenericStandardDeckGame(List<StandardCard> deck, int numPlayers) {
    if (numPlayers < 1) {
      throw new IllegalArgumentException("Please enter a valid number of players");
    }
    if (isDeckValid(deck) == false) {
      throw new IllegalArgumentException("Please enter a valid deck");
    } else {
      this.deck = deck;
      this.players = new ArrayList<Player>();
      this.startPlay(numPlayers, deck);
    }
  }

}


//changed the constructor so the cards were not dealt upon creation
//should our tests on genericStandardCardGame be resubmitted?

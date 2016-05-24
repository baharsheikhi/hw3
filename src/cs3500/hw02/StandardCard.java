package cs3500.hw02;

import java.util.Objects;

/**
 * A class to represent a standard card. It can be ranks 2-Ace with the four suits. Jokers are
 * excluded.
 */
public class StandardCard implements Comparable<StandardCard> {

  /**
   * @param suit The suit as a String (plural form ie. "Hearts", "Spades", "Diamonds", "Hearts"
   * @param rank The rank as a String 2-10, numbers in double quotes ie. "2", "3". The remaining
   *             ranks should be spelled out ie. "King", "Jack", "Queen", "Ace"
   */
  public StandardCard(Suit suit, Rank rank) {
    switch (suit) {
      case Hearts:
        this.suit = Suit.Hearts;
        break;
      case Diamonds:
        this.suit = Suit.Diamonds;
        break;
      case Clubs:
        this.suit = Suit.Clubs;
        break;
      case Spades:
        this.suit = Suit.Spades;
        break;
      default:
        throw new IllegalArgumentException("Please enter a valid suit");
    }

    switch (rank) {
      case Two:
        this.rank = Rank.Two;
        break;
      case Three:
        this.rank = Rank.Three;
        break;
      case Four:
        this.rank = Rank.Four;
        break;
      case Five:
        this.rank = Rank.Five;
        break;
      case Six:
        this.rank = Rank.Six;
        break;
      case Seven:
        this.rank = Rank.Seven;
        break;
      case Eight:
        this.rank = Rank.Eight;
        break;
      case Nine:
        this.rank = Rank.Nine;
        break;
      case Ten:
        this.rank = Rank.Ten;
        break;
      case Jack:
        this.rank = Rank.Jack;
        break;
      case Queen:
        this.rank = Rank.Queen;
        break;
      case King:
        this.rank = Rank.King;
        break;
      case Ace:
        this.rank = Rank.Ace;
        break;
      default:
        throw new IllegalArgumentException("Please enter valid rank.");
    }
  }

  private final Rank rank;
  private final Suit suit;
  public static final int MAX_RANK_VALUE = 14;
  public static final int MIN_RANK_VALUE = 2;
  public static final int DECK_SIZE = 52;

  @Override
  public String toString() {
    String ret = "";
    ret += this.rank.toString();
    ret += this.suit.toString();

    return ret;
  }

  @Override
  public int compareTo(StandardCard card) {
    if (this.suit.compareSuits(card.suit) > 0) {
      return 1;
    } else if (this.suit.compareSuits(card.suit) < 0) {
      return -1;
    } else {
      return this.rank.compareRank(card.rank);
    }
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (!(obj instanceof StandardCard)) {
      return false;
    } else {
      StandardCard that = (StandardCard) obj;
      return that.suit.equals(this.suit) && that.rank.equals(this.rank);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.suit, this.rank);
  }

}

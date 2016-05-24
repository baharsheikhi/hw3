package cs3500.hw02;

/**
 * An enumeration to represent suits of a card
 */
public enum Suit {
  Clubs, Diamonds, Hearts, Spades;

  @Override
  public String toString() {
    String ret = "";
    switch (this) {
      case Hearts:
        ret += "♥";
        break;
      case Spades:
        ret += "♠";
        break;
      case Diamonds:
        ret += "♦";
        break;
      case Clubs:
        ret += "♣";
        break;
      default:
    }

    return ret;
  }

  /**
   * @param s the suit to compare this one with
   * @return 1 if this suit is greater than that suit, -1 if the other is greater, 0 otherwise.
   */
  public int compareSuits(Suit s) {
    return this.ordinal() - s.ordinal();
  }
}

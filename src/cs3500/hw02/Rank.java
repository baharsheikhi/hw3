package cs3500.hw02;

/**
 * Created by bahar on 5/16/16. An enumeration to represents suits of cards
 */
public enum Rank {
  Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10),
  Jack(11), Queen(12), King(13), Ace(14);

  /**
   * Constructs a rank based on its value
   *
   * @param value the value of the rank
   */
  Rank(int value) {
    this.value = value;
  }

  private final int value;
  public static final int RANK_NUMBER = 12;

  /**
   * @return the value of the Rank
   */
  public String toString() {
    switch (this) {
      case Jack:
        return "J";
      case Queen:
        return "Q";
      case King:
        return "K";
      case Ace:
        return "A";
      default:
        return String.valueOf(this.value);
    }
  }

  /**
   * @return the integer value of this rank
   */
  private int rankToInt() {
    return this.value;
  }

  /**
   * @param r the rank to compare this one to
   * @return 1 if this rank is greater than r -1 if that is greater than this 0 otherwise
   */
  public int compareRank(Rank r) {
    return r.rankToInt() - this.rankToInt();
  }

  /**
   * @param i the ordinal value of the rank you are looking for
   * @return the parameter that has the ordinal value i
   */
  public static Rank intToRank(int i) {
    switch (i) {
      case 2:
        return Two;
      case 3:
        return Three;
      case 4:
        return Four;
      case 5:
        return Five;
      case 6:
        return Six;
      case 7:
        return Seven;
      case 8:
        return Eight;
      case 9:
        return Nine;
      case 10:
        return Ten;
      case 11:
        return Jack;
      case 12:
        return Queen;
      case 13:
        return King;
      case 14:
        return Ace;
      default:
        throw new IllegalArgumentException("Please enter a valid ordinal value");
    }
  }

}



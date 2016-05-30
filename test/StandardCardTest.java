import cs3500.hw02.Rank;
import cs3500.hw02.StandardCard;
import cs3500.hw02.Suit;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bahar on 5/18/16.
 * a class to test the standardCard method
 */
public class StandardCardTest {

  StandardCard aceSpades = new StandardCard(Suit.Spades, Rank.Ace);
  StandardCard twoSpades = new StandardCard(Suit.Spades, Rank.Two);
  StandardCard threeSpades = new StandardCard(Suit.Spades, Rank.Three);
  StandardCard fourSpades = new StandardCard(Suit.Spades, Rank.Four);
  StandardCard fiveSpades = new StandardCard(Suit.Spades, Rank.Five);
  StandardCard sixSpades = new StandardCard(Suit.Spades, Rank.Six);
  StandardCard sevenSpades = new StandardCard(Suit.Spades, Rank.Seven);
  StandardCard eightSpades = new StandardCard(Suit.Spades, Rank.Eight);
  StandardCard nineSpades = new StandardCard(Suit.Spades, Rank.Nine);
  StandardCard tenSpades = new StandardCard(Suit.Spades, Rank.Ten);
  StandardCard jackSpades = new StandardCard(Suit.Spades, Rank.Jack);
  StandardCard queenSpades = new StandardCard(Suit.Spades, Rank.Queen);
  StandardCard kingSpades = new StandardCard(Suit.Spades, Rank.King);

  StandardCard fiveHearts = new StandardCard(Suit.Hearts, Rank.Five);
  StandardCard tenHearts = new StandardCard(Suit.Hearts, Rank.Ten);

  StandardCard queenClubs = new StandardCard(Suit.Clubs, Rank.Queen);
  StandardCard kingClubs = new StandardCard(Suit.Clubs, Rank.King);

  StandardCard tenDiamonds = new StandardCard(Suit.Diamonds, Rank.Ten);

  Rank aceCopy = Rank.Ace;
  StandardCard aceSpadesCopy = new StandardCard(Suit.Spades, aceCopy);

  Suit heartCopy = Suit.Hearts;
  StandardCard queenHeartsCopy = new StandardCard(heartCopy, Rank.Queen);


  //tests that the constructor properly links the Ranks
  @Test
  public void testConstructorRanks() {
    assertEquals(fiveSpades, fiveSpades);
    assertEquals(Rank.Ace, aceCopy);
    assertEquals(aceSpades, aceSpadesCopy);
    assertFalse(aceSpades.equals(nineSpades));
  }

  //tests that the constructor properly links the Suit
  @Test
  public void testConstructorSuit() {
    assertEquals(Suit.Hearts, heartCopy);
    assertNotEquals(queenHeartsCopy, queenClubs);
    assertNotEquals(fiveHearts, tenHearts);
    assertEquals(aceSpades, aceSpadesCopy);
  }

  @Test
  public void testCompareToEquality() {
    assertEquals(aceSpades, aceSpadesCopy);
    assertEquals(0, aceSpades.compareTo(aceSpadesCopy));
    assertFalse(queenClubs.equals(kingClubs));
    assertFalse(queenClubs.compareTo(kingClubs) == 0);
    assertFalse(queenClubs.equals(queenSpades));
    assertFalse(queenClubs.compareTo(queenSpades) == 0);
    assertFalse(queenClubs.equals(fiveHearts));
    assertFalse(queenClubs.compareTo(fiveHearts) == 0);
  }

  @Test
  public void testComparetoInequality() {
    assertNotEquals(queenClubs, queenSpades);
    assertEquals(1, queenClubs.compareTo(queenSpades));
    assertEquals(1, tenDiamonds.compareTo(tenHearts));
    assertEquals(1, kingClubs.compareTo(kingSpades));
    assertEquals(1, Suit.Clubs.compareSuits(Suit.Spades));
    assertEquals(-1, queenSpades.compareTo(queenClubs));
    assertNotEquals(fiveHearts, tenHearts);
    assertEquals(1, tenHearts.compareTo(fiveHearts));
    assertEquals(-1, fiveHearts.compareTo(tenHearts));
    assertNotEquals(jackSpades, tenDiamonds);
    assertEquals(1, tenDiamonds.compareTo(jackSpades));
    assertEquals(-1, jackSpades.compareTo(tenDiamonds));
  }



  @Test
  public void testToString() {
    assertEquals("A♠", aceSpades.toString());
    assertEquals("2♠", twoSpades.toString());
    assertEquals("3♠", threeSpades.toString());
    assertEquals("4♠", fourSpades.toString());
    assertEquals("5♠", fiveSpades.toString());
    assertEquals("6♠", sixSpades.toString());
    assertEquals("7♠", sevenSpades.toString());
    assertEquals("8♠", eightSpades.toString());
    assertEquals("9♠", nineSpades.toString());
    assertEquals("10♠", tenSpades.toString());
    assertEquals("J♠", jackSpades.toString());
    assertEquals("Q♠", queenSpades.toString());
    assertEquals("K♠", kingSpades.toString());
    assertNotEquals(queenClubs.toString(), queenSpades.toString());
    assertNotEquals(fiveSpades.toString(), fiveHearts.toString());
    assertNotEquals(tenDiamonds, jackSpades);
    assertTrue(queenClubs.toString().contains("♣"));
    assertFalse(kingSpades.toString().contains("♣"));
  }

}
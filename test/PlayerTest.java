import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Player;
import cs3500.hw02.Rank;
import cs3500.hw02.StandardCard;
import cs3500.hw02.Suit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by bahar on 5/18/16. To test the player class
 */
public class PlayerTest {
  List<StandardCard> player1Hand = new ArrayList<StandardCard>();
  Player player1 = new Player();
  StandardCard aceSpades = new StandardCard(Suit.Spades, Rank.Ace);
  StandardCard aceClubs = new StandardCard(Suit.Clubs, Rank.Ace);
  StandardCard aceSpadesCopy = new StandardCard(Suit.Spades, Rank.Ace);
  StandardCard fiveClubs = new StandardCard(Suit.Clubs, Rank.Five);
  StandardCard kingHearts = new StandardCard(Suit.Hearts, Rank.King);
  StandardCard tenHearts = new StandardCard(Suit.Hearts, Rank.Ten);
  StandardCard sixSpades = new StandardCard(Suit.Spades, Rank.Six);
  StandardCard twoDiamonds = new StandardCard(Suit.Diamonds, Rank.Two);

  @Test
  public void testConstructor() {
    assertTrue(player1Hand.isEmpty());
  }

  @Test
  public void addCardTest() {
    player1Hand.add(aceSpades);
    assertEquals(1, player1Hand.size());
  }

  @Test
  public void toStringTest() {
    player1.addCard(aceSpades);
    assertEquals("A♠", player1.toString());
    player1.addCard(aceClubs);
    assertEquals("A♣, A♠", player1.toString());
    player1.addCard(aceSpadesCopy);
    assertEquals("A♣, A♠, A♠", player1.toString());
    player1.addCard(fiveClubs);
    assertEquals("A♣, 5♣, A♠, A♠", player1.toString());
    player1.addCard(kingHearts);
    assertEquals("A♣, 5♣, K♥, A♠, A♠", player1.toString());
    player1.addCard(tenHearts);
    assertEquals("A♣, 5♣, K♥, 10♥, A♠, A♠", player1.toString());
    player1.addCard(sixSpades);
    player1.addCard(twoDiamonds);
    assertEquals("A♣, 5♣, 2♦, K♥, 10♥, A♠, A♠, 6♠", player1.toString());
  }

  @Test
  public void countCardTest() {
    player1.addCard(aceSpades);
    player1.addCard(aceClubs);
    player1.addCard(aceSpadesCopy);
    player1.addCard(fiveClubs);
    player1.addCard(kingHearts);
    player1.addCard(tenHearts);
    player1.addCard(sixSpades);
    player1.addCard(twoDiamonds);
    assertEquals(8, player1.cardCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeCardTestExceptions() {
    player1.addCard(aceSpades);
    player1.addCard(aceClubs);
    player1.addCard(aceSpadesCopy);
    player1.addCard(fiveClubs);
    player1.addCard(kingHearts);
    player1.addCard(tenHearts);
    player1.addCard(sixSpades);
    player1.addCard(twoDiamonds);
    player1.removeCard(8);
    player1.removeCard(20);

  }

  @Test
  public void removeCardTest() {
    player1.addCard(aceSpades);
    player1.addCard(aceClubs);
    player1.addCard(aceSpadesCopy);
    player1.addCard(fiveClubs);
    player1.addCard(kingHearts);
    player1.addCard(tenHearts);
    player1.addCard(sixSpades);
    player1.addCard(twoDiamonds);
    assertEquals(8, player1.cardCount());
    assertEquals(aceSpades, player1.removeCard(0));
    assertEquals(7, player1.cardCount());
  }

}
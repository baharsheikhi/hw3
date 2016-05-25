import com.sun.xml.internal.bind.api.impl.NameConverter;

import org.junit.Test;

import cs3500.hw02.Rank;
import cs3500.hw02.StandardCard;
import cs3500.hw02.Suit;
import cs3500.hw03.WhistModel;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Created by bahar on 5/23/16.
 * to test the whistModel class
 */
public class WhistModelTest {

  //imagine this is a game with 4 players and each player has 13 cards in their hands
  WhistModel model1 = new WhistModel();
  //imagine this is a game with 3 players and player 1 has 18 cards, 2 and 3 have 17

  @Test(expected = IllegalArgumentException.class)
  public void playExceptions() {
    model1.play(4, 0);
    model1.play(0, 13);
    model1.play(5, 17);
  }

  @Test
  public void testPlay() {
    String before = model1.getGameState();
    String [] beforeSplitByPlayer = before.split("player");

    String player1 = beforeSplitByPlayer[1];
    String player2 = beforeSplitByPlayer[2];
    String player3 = beforeSplitByPlayer[3];
    String player4 = beforeSplitByPlayer[4];

    String player1Trick = beforeSplitByPlayer[5];
    String player2Trick = beforeSplitByPlayer[6];
    String player3Trick = beforeSplitByPlayer[7];
    String player4Trick = beforeSplitByPlayer[8];

    String specialMessage = beforeSplitByPlayer[9];

    String [] p1 = player1.split(":");
    String player1HandAsString = p1[1];
    String [] player1Hand = player1HandAsString.split(",");
    String theRemoved = player1Hand[0];

    String [] p2 = player2.split(":");
    String player2HandAsString = p2[1];
    String [] player2Hand = player2HandAsString.split(",");

    String [] p3 = player3.split(":");
    String player3HandAsString = p3[1];
    String [] player3Hand = player3HandAsString.split(",");

    String [] p4 = player4.split(":");
    String player4HandAsString = p4[1];
    String [] player4Hand = player4HandAsString.split(",");

    //testing the initial conditions
    assertEquals(13, player1Hand.length);
    assertEquals(13, player2Hand.length);
    assertEquals(13, player3Hand.length);
    assertEquals(13, player4Hand.length);

    model1.play(0, 0);

    String after = model1.getGameState();
    String [] afterSplitByPlayer = after.split("player");

    String player1After = afterSplitByPlayer[1];
    String player2After = afterSplitByPlayer[2];
    String player3After = afterSplitByPlayer[3];
    String player4After = afterSplitByPlayer[4];

    String player1TrickAfter = afterSplitByPlayer[5];
    String player2TrickAfter = afterSplitByPlayer[6];
    String player3TrickAfter = afterSplitByPlayer[7];
    String player4TrickAfter = afterSplitByPlayer[8];

    String specialMessageAfter = afterSplitByPlayer[9];

    //TODO what happens to the special message?

    String [] p1After = player1After.split(":");
    String player1HandAsStringAfter = p1After[1];
    String [] player1HandAfter = player1HandAsStringAfter.split(",");
    String nowInPos0 = player1HandAfter[0];

    String [] p2After = player2After.split(":");
    String player2HandAsStringAfter = p2After[1];
    String [] player2HandAfter = player2HandAsStringAfter.split(",");

    String [] p3After = player3After.split(":");
    String player3HandAsStringAfter = p3After[1];
    String [] player3HandAfter = player3HandAsStringAfter.split(",");

    String [] p4After = player4After.split(":");
    String player4HandAsStringAfter = p4After[1];
    String [] player4HandAfter = player4HandAsStringAfter.split(",");


    //something was removed
    assertEquals(12, player1HandAfter.length);
    //something new should be in the position
    assertFalse(theRemoved.equals(nowInPos0));
    //nothing else should be changed
    assertEquals(13, player2HandAfter.length);
    assertEquals(13, player3HandAfter.length);
    assertEquals(13, player4HandAfter.length);
    assertEquals(player1Trick, player1TrickAfter);
    assertEquals(player2Trick, player2TrickAfter);
    assertEquals(player3Trick, player3TrickAfter);
    assertEquals(player4Trick, player4TrickAfter);

  }

  @Test (expected = IllegalArgumentException.class)
  public void getCurrentPlayerExceptions() {
  }

  @Test
  public void simpleGameTest() {
    assertFalse(model1.isGameOver());
    //should always start with 0
    assertEquals(0, model1.getCurrentPlayer());
    //the first player moves : 12 cards left
    model1.play(0, 0);
    //now it is the second player's turn
    assertEquals(1, model1.getCurrentPlayer());
    //the second player moves: 12 cards left
    model1.play(1, 0);
    //now it is the third player's turn
    assertEquals(2, model1.getCurrentPlayer());
    //the third player moves: 13 cards left
    model1.play(2, 0);
    //now it is the fourth player's turn
    assertEquals(3, model1.getCurrentPlayer());
    //the fourth player moves
    model1.play(3, 0);
    //IMPORTANT: NOW IT IS THE FIRST PLAYER'S TURN AGAIN
    assertEquals(0, model1.getCurrentPlayer());
    assertFalse(model1.isGameOver());
    model1.play(0, 0); // 11 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //10 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //9 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //8 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //7 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //6 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //5 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //4 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //3 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //2 cards left
    model1.play(1, 0);
    model1.play(2, 0);
    model1.play(3, 0);

    assertFalse(model1.isGameOver());
    model1.play(0, 0); //1 card left
    //throws an exception because it is not model1's turn
    try {
      model1.play(0, 0);
    }
    catch (IllegalArgumentException e) {
      this.playExceptions();
    }
    assertFalse(model1.isGameOver());
    model1.play(1, 0); //1 card left
    assertFalse(model1.isGameOver());
    model1.play(2, 0); //1 card left
    assertFalse(model1.isGameOver());
    model1.play(3, 0);
    assertTrue(model1.isGameOver());
    //is this hairy, calling play after the game is over?
    //should throw an exception if you're trying to move once the game is over
    try {
      model1.play(0, 0);
    }
    catch (IllegalArgumentException e) {
      this.playExceptions();
    }

    //throws an exception because the game is over
    try {
      model1.getCurrentPlayer();
    }
    catch (IllegalArgumentException e) {
      this.getCurrentPlayerExceptions();
    }
  }

  @Test
  public void testSkipPlayerPlay() {
    List<StandardCard> shortList = new ArrayList<StandardCard>();
    //X
    shortList.add(new StandardCard(Suit.Hearts, Rank.Two));
    //Y
    shortList.add(new StandardCard(Suit.Hearts, Rank.Three));
    //Z
    shortList.add(new StandardCard(Suit.Hearts, Rank.Five));
    //A
    shortList.add(new StandardCard(Suit.Spades, Rank.Two));
    //B
    shortList.add(new StandardCard(Suit.Spades, Rank.Three));
    //C
    shortList.add(new StandardCard(Suit.Spades, Rank.Five));
    //D
    shortList.add(new StandardCard(Suit.Diamonds, Rank.Two));
    //E
    shortList.add(new StandardCard(Suit.Diamonds, Rank.Ace));
    //F
    shortList.add(new StandardCard(Suit.Diamonds, Rank.Three));
    //G
    shortList.add(new StandardCard(Suit.Clubs, Rank.Two));
    //H
    shortList.add(new StandardCard(Suit.Clubs, Rank.Ace));

    WhistModel modelSkip = null;
    try {
      modelSkip = new WhistModel(shortList, 3);
    } catch (IllegalArgumentException e) {
      modelSkip.play(0, 2);
      modelSkip.play(0, 0);
      modelSkip.play(0, 1);
      //player 3 should win here
      modelSkip.play(0, 2);
      modelSkip.play(0, 0);
      modelSkip.play(0, 1);
      //player 3 should win again
      modelSkip.play(0, 2);
      modelSkip.play(0, 0);
      modelSkip.play(0, 1);
      //player 2 should win here
      //because player 1 does not have any more cards
      modelSkip.play(0, 1);
      try {
        modelSkip.play(0, 0);
      } catch (IllegalArgumentException f) {
        this.playExceptions();
      }
      modelSkip.play(0, 2);
      assertTrue(modelSkip.isGameOver());
    }
  }

  @Test
  public void isGameOver() {
    //kind of already done in the previous test...... :/
    //TODO is this okay?
  }

}
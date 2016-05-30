import cs3500.hw02.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * a class to test the GenericStandardDeckGame
 */
public class GenericStandardDeckGameTest {

  GenericCardGameModel<StandardCard> gameBasic = new GenericStandardDeckGame();
  List<StandardCard> deck1 = gameBasic.getDeck();
  GenericStandardDeckGame gameCustomNumPlayers = new GenericStandardDeckGame(2);
  List<StandardCard> badDeck = new ArrayList<StandardCard>();
  GenericStandardDeckGame gameCustomDeck =
          new GenericStandardDeckGame(gameCustomNumPlayers.getDeck());
  GenericStandardDeckGame gameOnePlayer =
          new GenericStandardDeckGame(gameCustomNumPlayers.getDeck(), 1);


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorCustomDeck() {
    badDeck.add(new StandardCard(Suit.Diamonds, Rank.Ace));
    GenericStandardDeckGame gameBadDeck =
            new GenericStandardDeckGame(badDeck);
    badDeck.addAll(gameCustomNumPlayers.getDeck());
    GenericStandardDeckGame gameBadDeck1 =
            new GenericStandardDeckGame(badDeck);


  }
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorCustomPlayers() {
    GenericStandardDeckGame gameBadNumberPlayers =
            new GenericStandardDeckGame(0);
    GenericStandardDeckGame gameBadNumberPlayers1 =
            new GenericStandardDeckGame(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorCustomAll() {
    badDeck.add(new StandardCard(Suit.Diamonds, Rank.Ace));
    GenericStandardDeckGame gameBadNumberPlayers =
            new GenericStandardDeckGame(gameCustomNumPlayers.getDeck(), 0);
    GenericStandardDeckGame gameBadDeck =
            new GenericStandardDeckGame(badDeck, 4);
    GenericStandardDeckGame gameBadBoth =
            new GenericStandardDeckGame(badDeck, 0);
  }
  //tests that the deck has 52 cards
  @Test
  public void getDeckContents() {
    assertEquals(52, gameBasic.getDeck().size());
    assertFalse(gameBasic.getDeck().equals(null));

    for (int i = 0; i < gameBasic.getDeck().size(); i++) {
      assertFalse(gameBasic.getDeck().get(i).equals(null));
    }
  }

  //tests that there are 4 of each rank
  @Test
  public void getDeckRankCount() {
    int rankTwo = 0;
    int rankThree = 0;
    int rankFour = 0;
    int rankFive = 0;
    int rankSix = 0;
    int rankSeven = 0;
    int rankEight = 0;
    int rankNine = 0;
    int rankTen = 0;
    int rankJack = 0;
    int rankQueen = 0;
    int rankKing = 0;
    int rankAce = 0;

    for(int i = 0; i < StandardCard.DECK_SIZE; i++) {
      String str = gameBasic.getDeck().get(i).toString();
      if (str.contains("2")) {
        rankTwo++;
      } else if (str.contains("3")) {
        rankThree++;
      } else if (str.contains("4")) {
        rankFour++;
      } else if (str.contains("5")) {
        rankFive++;
      } else if (str.contains("6")) {
        rankSix++;
      } else if (str.contains("7")) {
        rankSeven++;
      } else if (str.contains("8")) {
        rankEight++;
      } else if (str.contains("9")) {
        rankNine++;
      } else if (str.contains("10")) {
        rankTen++;
      } else if (str.contains("J")) {
        rankJack++;
      } else if (str.contains("Q")) {
        rankQueen++;
      } else if (str.contains("K")) {
        rankKing++;
      } else if (str.contains("A")) {
        rankAce++;
      }
    }

      assertEquals(4, rankTwo);
      assertEquals(4, rankThree);
      assertEquals(4, rankFour);
      assertEquals(4, rankFive);
      assertEquals(4, rankSix);
      assertEquals(4, rankSeven);
      assertEquals(4, rankEight);
      assertEquals(4, rankNine);
      assertEquals(4, rankTen);
      assertEquals(4, rankJack);
      assertEquals(4, rankQueen);
      assertEquals(4, rankKing);
      assertEquals(4, rankAce);
  }

  //Each suit should appear 13 times
  @Test
  public void testSuitCount() {
    int heartsCount = 0;
    int spadesCount = 0;
    int clubsCount = 0;
    int diamondsCount = 0;

    for(int i = 0; i < StandardCard.DECK_SIZE; i++) {
      String str = gameBasic.getDeck().get(i).toString();
      if (str.contains("♥")) {
        heartsCount++;
      }

      else if (str.contains("♠")) {
        spadesCount++;
      }

      else if (str.contains("♣")) {
        clubsCount++;
      }

      else if (str.contains("♦")) {
        diamondsCount++;
      }
    }

    assertEquals(13, heartsCount);
    assertEquals(13, spadesCount);
    assertEquals(13, clubsCount);
    assertEquals(13, diamondsCount);
  }

  @Test
  public void noDuplicatesGetDeck() {
    List<StandardCard> copiedDeck = new ArrayList<StandardCard>();
    copiedDeck.addAll(this.gameBasic.getDeck());

    List<StandardCard> transferredDeck = new ArrayList<StandardCard>();
    transferredDeck.addAll(this.gameBasic.getDeck());

    for(int i = 0; i < this.gameBasic.getDeck().size(); i++) {
      StandardCard discarded = copiedDeck.remove(0);

      for (StandardCard s : copiedDeck) {
        assertFalse(discarded.equals(s));
      }
    }
  }

  @Test
  public void differentDeckEachTime() {
    GenericStandardDeckGame gameBasicCompare = new GenericStandardDeckGame();
    String basicGameState = gameBasic.getGameState();
    String basicGameStateCompare = gameBasicCompare.getGameState();

    assertFalse(basicGameState.equals(basicGameStateCompare));
  }

  @Test
  public void startPlay() {
//     this.gameBasic.startPlay(4, this.gameBasic.getDeck());
  }

  //tests that the deck distributed is still valid after being given to the players
  @Test
  public void getGameStateTestValidDeck() {
    String gameState = gameCustomNumPlayers.getGameState();

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

      for (int i = 0; i < gameState.length(); i++) {
       switch (gameState.charAt(i)) {
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
      assertEquals(13, clubsCount);
      assertEquals(13, diamondsCount);
      assertEquals(13, heartsCount);
      assertEquals(13, spadesCount);
      //Players: 2, and Player 2 both count as the two character
      assertEquals(6, twoCount);
      assertEquals(4, threeCount);
      assertEquals(4, fourCount);
      assertEquals(4, fiveCount);
      assertEquals(4, sixCount);
      assertEquals(4, sevenCount);
      assertEquals(4, eightCount);
      assertEquals(4, nineCount);
      //player 1 as well as everytime 10
      assertEquals(5, tenCount);
      assertEquals(4, jackCount);
      assertEquals(4, queenCount);
      assertEquals(4, kingCount);
      assertEquals(4, aceCount);
  }

   @Test(expected = IllegalArgumentException.class)
   public void testStartPlayAlreadyInitializedException(){
     gameCustomNumPlayers.startPlay(2, gameCustomNumPlayers.getDeck());
   }

  @Test(expected = IllegalArgumentException.class)
  public void testStartPlayInvalidDeck() {
    badDeck.add(new StandardCard(Suit.Diamonds, Rank.Two));
    gameCustomNumPlayers.startPlay(5, badDeck);
    badDeck.addAll(gameCustomNumPlayers.getDeck());
  }

    @Test
    public void testStartPlayNoShuffle() {
    String gameState = gameCustomDeck.getGameState();
    String fromGameState = "";
    String fromDeck = "";
    List<StandardCard> deck = gameCustomNumPlayers.getDeck();
    Collections.sort(deck);
    for (StandardCard c : deck) {
      fromDeck += c.toString();
    }
    String player1String = "";
    String player2String = "";
    String player3String = "";
    String player4String = "";

    String[] playersHands = gameState.split("Player");

    String[] player1HandBefore = playersHands[1].split(":");
    String player1HandString = player1HandBefore[1];
    String[] player1Hand = player1HandString.split(",");
    for (String s : player1Hand) {
      if (!s.equals(" \n")) {
        fromGameState += s;
      }
    }

    String[] player2HandBefore = playersHands[2].split(":");
    String player2HandString = player2HandBefore[1];
    String[] player2Hand = player2HandString.split(",");
    for (String s : player2Hand) {
      if (!s.equals(" \n")) {
        fromGameState += s;
      }
    }

    String[] player3HandBefore = playersHands[3].split(":");
    String player3HandString = player3HandBefore[1];
    String[] player3Hand = player3HandString.split(",");
    for (String s : player3Hand) {
      if (!s.equals(" \n")) {
        fromGameState += s;
      }
    }

    String[] player4HandBefore = playersHands[4].split(":");
    String player4HandString = player4HandBefore[1];
    String[] player4Hand = player4HandString.split(",");
    for (String s : player4Hand) {
      if (!s.equals(" \n")) {
        fromGameState += s;
      }
    }

    fromGameState = fromGameState.replaceAll(" ", "");
    //14 with the "\n"
      assertEquals(fromDeck, fromGameState);
      assertEquals(player1Hand.length, 14);
      assertEquals(player2Hand.length, 14);
      assertEquals(player3Hand.length, 14);
      assertEquals(player4Hand.length, 14);
  }

  @Test
  public void testOnePlayer() {
    String gameState = gameOnePlayer.getGameState();
    String player1String = "";
    String fromDeck = "";
    List<StandardCard> deck = gameCustomNumPlayers.getDeck();
    Collections.sort(deck);
    for (StandardCard c: deck) {
      fromDeck += c.toString();
    }

    String[] playersHands = gameState.split("Player");

    String[] player1HandBefore = playersHands[1].split(":");
    String player1HandString = player1HandBefore[1];
    String [] player1Hand = player1HandString.split(",");
    for (int i = 0; i < 52; i++) {
      player1String+= player1Hand[i];
    }

    player1String = player1String.replaceAll(" ", "");
    assertEquals(fromDeck, player1String);

  }

  @Test
  public void testGameStateExpected() {
   assertEquals("Number of players: 4\n" +
           "Player 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, \n" +
           "Player 2: A♦, K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, \n" +
           "Player 3: A♥, K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, \n" +
           "Player 4: A♠, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, \n",
           gameCustomDeck.getGameState());
  }

}


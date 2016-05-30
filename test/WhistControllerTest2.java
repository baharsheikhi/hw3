import cs3500.hw03.WhistController;
import cs3500.hw03.WhistModel;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

import java.io.StringReader;

public class WhistControllerTest2 {

    @Test
    public void test() {
        StringBuffer out = new StringBuffer();
        new WhistController(new StringReader("1"), out).playGame(new WhistModel(), 4);
        assertEquals("Number of players: 4\n" +
                "Player 1: A♣, Q♣, 10♣, 9♣, 8♣, 7♣, 4♣, 3♣, K♦, 6♦, 4♦, Q♠, 5♠\n" +
                "Player 2: K♣, 2♣, A♦, Q♦, 8♦, 5♦, 2♦, A♥, K♥, J♥, 9♥, J♠, 4♠\n" +
                "Player 3: J♣, 6♣, 5♣, J♦, 10♥, 8♥, 7♥, 6♥, 10♠, 9♠, 7♠, 6♠, 3♠\n" +
                "Player 4: 10♦, 9♦, 7♦, 3♦, Q♥, 5♥, 4♥, 3♥, 2♥, A♠, K♠, 8♠, 2♠\n" +
                "Player 1 score: 0\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n" +
                "Turn: Player 1Number of players: 4\n" +
                "Player 1: Q♣, 10♣, 9♣, 8♣, 7♣, 4♣, 3♣, K♦, 6♦, 4♦, Q♠, 5♠\n" +
                "Player 2: K♣, 2♣, A♦, Q♦, 8♦, 5♦, 2♦, A♥, K♥, J♥, 9♥, J♠, 4♠\n" +
                "Player 3: J♣, 6♣, 5♣, J♦, 10♥, 8♥, 7♥, 6♥, 10♠, 9♠, 7♠, 6♠, 3♠\n" +
                "Player 4: 10♦, 9♦, 7♦, 3♦, Q♥, 5♥, 4♥, 3♥, 2♥, A♠, K♠, 8♠, 2♠\n" +
                "Player 1 score: 0\n" +
                "Player 2 score: 0\n" +
                "Player 3 score: 0\n" +
                "Player 4 score: 0\n" +
                "Turn: Player 2", out.toString());
    }

}

package cs3500.hw03;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by bahar on 5/23/16.
 * The controller of the whist game
 */
public class WhistController implements IWhistController {

    private final Scanner input;
    private final Appendable output;

    /**
     * Creates a whist controller with the given input and output
     *
     * @param rd The readable
     * @param ap The appendable
     */
    public WhistController(Readable rd, Appendable ap) {
        this.input = new Scanner(rd);
        this.output = ap;
    }

    @Override
    public <K> void playGame(CardGameModel<K> game, int numPlayers) {
        if (game == null) {
            throw new IllegalArgumentException("Please enter a game that is not null");
        }
        if (numPlayers < 2) {
            throw new IllegalArgumentException("Please enter a valid number of players");
        }

        game.startPlay(numPlayers, game.getDeck());

        while (!game.isGameOver()) {

            try {
                output.append(game.getGameState());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                String in = input.next();
                Integer cardToPlay;


                try {
                    cardToPlay = Integer.parseInt(in);
                    try {
                        game.play(game.getCurrentPlayer(), (cardToPlay - 1));
                    } catch (IllegalArgumentException e) {
                        try {
                            output.append("Try again. Invalid input.");
                            if (e.getMessage().equals("Please enter a valid card index")) {
                                output.append(" Please enter a valid card number.\n");
                            }
                            if (e.getMessage().equals("Please choose a card of the correct Suit")) {
                                output.append(" Please choose a card of the correct Suit. ");
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (NumberFormatException n) {
                    if (in.equals("q")) {
                        try {
                            output.append("\nGame quit prematurely.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    } else {
                        try {

                            output.append("Try again. Invalid input\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (NoSuchElementException e) {
                return;
            }

        }

        try {
            output.append(game.getGameState());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}

//Main method I used to play the game:
class Runner {

    public static void main(String[] args) {
        new WhistController(new InputStreamReader(System.in),
 System.out).playGame(new WhistModel(), 4);
    }
}

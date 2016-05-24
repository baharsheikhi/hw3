package cs3500.hw03;

/**
 * Created by bahar on 5/23/16.
 */
public interface IWhistController {

  /**
   * should start the provided game model.
   * It should throw an IllegalArgumentException only if the model is
   * null or number of players passed to it is invalid.
   * @param game the game that is being played
   * @param numPlayers the number of players in the game
   * @param <K> the type of card being used
   */
  <K> void playGame(CardGameModel<K> game, int numPlayers);
}

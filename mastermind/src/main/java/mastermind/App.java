package mastermind;

/**
 * Main class for the MasterMind app.
 *
 * @author Mauro Ezequiel Moltrasio
 */
public class App {
  private UserInterface ui;
  private Integer score;

  /**
   * Main constructor.
   *
   * <p>Will initialize the score counter and create a stream for reading stdin.
   */
  App() {
    ui = new UserInterface();
    score = 0;
  }

  /**
   * Play a single MasterMind game.
   *
   * <p>A game is played by creating a board and asking the user for row attempts until the tries
   * are exhausted or the secret code is guessed.
   *
   * <p>Once the game is done, the score for the game is returned.
   *
   * @return The final score as an Integer.
   */
  private Integer playGame() {
    Board board = new Board();
    Integer finalScore;

    while (!board.isGameOver()) {
      ui.printBoard(board);
      board.tryAnswer(ui.getRowFromPlayer());
    }

    finalScore = board.getFinalScore();

    ui.printBoard(board);
    ui.printGameOver(finalScore);

    return finalScore;
  }

  /**
   * Get the desired number of rounds a user wants to play.
   *
   * @return An integer with the number of rounds to play.
   */
  private Integer getRounds() {
    return ui.getRounds();
  }

  /**
   * Entry point to the MasterMind game.
   *
   * <p>Plays as many rounds of the game as the player wants and outputs the final score for them.
   *
   * @param args Arguments provided to the App. Currently ignored.
   */
  public static void main(String[] args) {
    App app = new App();

    app.ui.printWelcomeMessage();

    Integer rounds = app.getRounds();

    for (int i = 0; i < rounds; i++) {
      app.score += app.playGame();
    }

    app.ui.printFinalMessage(app.score);
  }
}

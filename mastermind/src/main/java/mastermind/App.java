package mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Main class for the MasterMind app.
 *
 * @author Mauro Ezequiel Moltrasio
 */
public class App {
  private BufferedReader br;
  private Integer score;

  /**
   * Main constructor.
   *
   * <p>Will initialize the score counter and create a stream for reading stdin.
   */
  App() {
    br = new BufferedReader(new InputStreamReader(System.in));
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
   * @throws NumberFormatException When the input by the user is not a number.
   * @throws IOException On system IO errors.
   */
  private Integer playGame() throws NumberFormatException, IOException {
    Board board = new Board();
    Integer finalScore;

    while (!board.isGameOver()) {
      System.out.println(board.tryAnswer(getRowFromPlayer()));
    }

    finalScore = board.getFinalScore();

    System.out.println("El juego a terminado!");
    System.out.println("Puntaje acumulado: " + finalScore);

    return finalScore;
  }

  /**
   * Ask the user for the number of rounds they want to play. Return the number of rounds as an
   * integer.
   *
   * @return The number of rounds to play.
   * @throws NumberFormatException When the input by the user is not a number.
   * @throws IOException On system IO errors.
   */
  private Integer getRounds() throws NumberFormatException, IOException {
    System.out.print("Ingrese el número de rondas que desea jugar: ");

    return Integer.parseInt(br.readLine());
  }

  /**
   * Asks the player for individual pegs in order to create a row to play.
   *
   * @return A row formed from the pegs chosen by the player.
   * @throws NumberFormatException When the input by the user is not a number.
   * @throws IOException On system IO errors.
   */
  private Row getRowFromPlayer() throws NumberFormatException, IOException {
    Peg[] pegs = new Peg[Row.PEG_COUNT];
    Color[] colors = PlayableColor.values();

    System.out.println("Forme su fila:");

    {
      int i = 1;
      for (Color color : colors) {
        System.out.println(i + ". " + color.getColor());
        i++;
      }
    }

    for (int i = 0; i < Row.PEG_COUNT; i++) {
      Color color = colors[Integer.parseInt(br.readLine()) - 1];
      pegs[i] = new Peg(color);

      System.out.println(Arrays.toString(pegs));
    }

    return new Row(pegs);
  }

  /**
   * Entry point to the MasterMind game.
   *
   * <p>Plays as many rounds of the game as the player wants and outputs the final score for them.
   *
   * @param args Arguments provided to the App. Currently ignored.
   * @throws NumberFormatException When the input by the user is not a number.
   * @throws IOException On system IO errors.
   */
  public static void main(String[] args) throws NumberFormatException, IOException {
    App app = new App();

    System.out.println("Bienvenido a MasterMind!");

    Integer rounds = app.getRounds();

    for (int i = 0; i < rounds; i++) {
      app.score += app.playGame();
    }

    System.out.println("Todas las rondas han terminado!");
    System.out.println("Su puntaje final es: " + app.score);
  }
}

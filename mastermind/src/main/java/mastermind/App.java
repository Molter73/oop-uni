package mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/** Hello world! */
public class App {
  private BufferedReader br;
  private Integer score;

  App() {
    br = new BufferedReader(new InputStreamReader(System.in));
    score = 0;
  }

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

  private Integer getRounds() throws NumberFormatException, IOException {
    System.out.print("Ingrese el número de rondas que desea jugar: ");

    return Integer.parseInt(br.readLine());
  }

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

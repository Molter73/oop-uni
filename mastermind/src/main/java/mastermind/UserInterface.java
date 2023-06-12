package mastermind;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInterface {
  private BufferedReader br;

  public UserInterface() {
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  /**
   * Ask the user for the number of rounds they want to play. Return the number of rounds as an
   * integer.
   *
   * @return The number of rounds to play.
   */
  public Integer getRounds() {
    while (true) {
      try {
        System.out.print("Ingrese el número de rondas que desea jugar: ");

        return Integer.parseInt(br.readLine());
      } catch (Exception e) {
        System.out.println("Número de ronda inválido: " + e.getMessage());
      }
    }
  }

  /**
   * Asks the player for individual pegs in order to create a row to play.
   *
   * @return A row formed from the pegs chosen by the player.
   */
  public Row getRowFromPlayer() {
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
      Color color;

      while (true) {
        try {
          color = colors[Integer.parseInt(br.readLine()) - 1];
          break;
        } catch (Exception e) {
          System.out.println("Selección inválida: " + e.getMessage());
        }
      }

      pegs[i] = new Peg(color);

      String output = "|";

      for (Peg peg : pegs) {
        if (peg != null) {
          output += peg.toString();
        } else {
          output += Color.useColors ? Color.smallDot : "      ";
        }
        output += "|";
      }

      System.out.println(output);
    }

    return new Row(pegs);
  }

  /**
   * Prints a board in a human friendly format.
   *
   * @param board The game board we want to print.
   */
  public void printBoard(Board board) {
    System.out.println(board.toString());
  }

  /** Print a welcome message for the player. */
  public void printWelcomeMessage() {
    System.out.println("Bienvenido a MasterMind!");
  }

  /**
   * Print a message when all games are done.
   *
   * @param score The final score for the player.
   */
  public void printFinalMessage(int score) {
    System.out.println("Todas las rondas han terminado!");
    System.out.println("Su puntaje final es: " + score);
  }

  /**
   * Print a message when a game is over.
   *
   * @param score The score the player got on the previous game.
   */
  public void printGameOver(int score) {
    System.out.println("El juego a terminado!");
    System.out.println("Puntaje acumulado: " + score);
  }
}

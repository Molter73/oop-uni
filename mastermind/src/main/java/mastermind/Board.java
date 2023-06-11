package mastermind;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representation for a MasterMind board.
 *
 * <p>Allows a single game to be played by continuously providing it with rows until the game is
 * over.
 */
public class Board {
  private static final Integer TOTAL_TRIES = 12;
  private ArrayList<Row> triedCodes;
  private ArrayList<Row> keyCodes;
  private Row hiddenCode;
  private boolean codeGuessed;

  private static final Row ALL_GUESSED_ROW =
      new Row(
          new Peg[] {
            new Peg(KeyColor.BLACK),
            new Peg(KeyColor.BLACK),
            new Peg(KeyColor.BLACK),
            new Peg(KeyColor.BLACK),
            new Peg(KeyColor.BLACK),
          });

  private static final Row EMPTY_ROW =
      new Row(
          new Peg[] {
            new Peg(KeyColor.EMPTY),
            new Peg(KeyColor.EMPTY),
            new Peg(KeyColor.EMPTY),
            new Peg(KeyColor.EMPTY),
            new Peg(KeyColor.EMPTY),
          });

  static final String boardLimit = Color.useColors ? "+-+-+-+-+-+" : "+------+------+------+------+------+";

  /**
   * Main constructor for the Board.
   *
   * <p>Also creates a random row for players to guess.
   */
  public Board() {
    triedCodes = new ArrayList<>();
    keyCodes = new ArrayList<>();
    codeGuessed = false;

    Peg[] pegs = new Peg[Row.PEG_COUNT];

    for (int i = 0; i < Row.PEG_COUNT; i++) {
      pegs[i] = new Peg(PlayableColor.getRandom());
    }

    hiddenCode = new Row(pegs);
  }

  /**
   * Return the list of rows the player has played.
   *
   * @return The list of rows played so far.
   */
  public ArrayList<Row> getTriedCodes() {
    return triedCodes;
  }

  /**
   * Return the list of rows with hints for each row the user has played.
   *
   * @return The list of rows with hints to each played row.
   */
  public ArrayList<Row> getKeyCodes() {
    return keyCodes;
  }

  /**
   * Return the hidden row. This should not be shown to the player.
   *
   * @return The row to be guessed.
   */
  public Row getHiddenCode() {
    return hiddenCode;
  }

  /**
   * Check whether the game has finished.
   *
   * <p>The game being finished means that either the player guessed the code or there are no more
   * tries available.
   *
   * @return A boolean specifying whether the game is over.
   */
  public boolean isGameOver() {
    return codeGuessed || triedCodes.size() == TOTAL_TRIES;
  }

  /**
   * Calculate the final score for the game. This should only be called once the game is over.
   *
   * <p>The score is calculated as the max number of tries minus the attempts required to win.
   *
   * @return The final score for the game.
   * @throws RuntimeException Thrown when attempting to get the final score in an ongoing game.
   */
  public Integer getFinalScore() throws RuntimeException {
    if (!isGameOver()) {
      throw new RuntimeException("game is not over yet");
    }

    return TOTAL_TRIES - triedCodes.size();
  }

  /**
   * Compare the provided row against the hidden one.
   *
   * <p>A row of hints will be returned, there are three possibilities:
   *
   * <ul>
   *   <li>A black peg indicates the color in the position matches the peg in the hidden code.
   *   <li>A white peg indicates the color provided by the user is part of the final solution, but
   *       it's in the wrong position.
   *   <li>No peg indicates the color is not part of the hidden code
   * </ul>
   *
   * <p>If the provided row matches the hidden code, the game is marked as over.
   *
   * @param answer A row of pegs provided by the player as a guess for the hidden code.
   * @return A row of pegs hinting the user as to what changes need to be made to match the hidden
   *     code.
   */
  public Row tryAnswer(Row answer) {
    triedCodes.add(answer);

    Peg[] answerPegs = answer.getPegs();
    Peg[] hiddenRowPegs = hiddenCode.getPegs();
    Peg[] hint = new Peg[Row.PEG_COUNT];

    for (int i = 0; i < Row.PEG_COUNT; i++) {
      if (answerPegs[i].equals(hiddenRowPegs[i])) {
        hint[i] = new Peg(KeyColor.BLACK);
      } else if (Arrays.asList(hiddenRowPegs).contains(answerPegs[i])) {
        int userCount = 0;
        int hiddenCount = 0;

        for (int j = 0; j < Row.PEG_COUNT; j++) {
          if (answerPegs[j].equals(answerPegs[i])) {
            userCount++;
          }

          if (hiddenRowPegs[j].equals(answerPegs[i])) {
            hiddenCount++;
          }
        }

        // If the user placed more elements of the same color than there are
        // in the final answer, then the key is an empty peg.
        hint[i] = userCount > hiddenCount ? new Peg(KeyColor.EMPTY) : new Peg(KeyColor.WHITE);
      } else {
        hint[i] = new Peg(KeyColor.EMPTY);
      }
    }

    Row hintRow = new Row(hint);
    keyCodes.add(hintRow);

    if (hintRow.equals(ALL_GUESSED_ROW)) {
      codeGuessed = true;
    }

    return hintRow;
  }

  @Override
  public String toString() {
    String output = boardLimit + boardLimit + "\n";

    for (int i = 0; i < TOTAL_TRIES ; i++) {
      output += i >= triedCodes.size() ? EMPTY_ROW : triedCodes.get(i).toString();
      output += i >= keyCodes.size() ? EMPTY_ROW : keyCodes.get(i).toString();
      output += "\n";
    }

    output += boardLimit + boardLimit + "\n";

    return output;
  }
}

package mastermind;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
  private static final Integer TOTAL_TRIES = 12;
  private ArrayList<Row> triedCodes;
  private ArrayList<Row> keyCodes;
  private Row hiddenRow;
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

  public Board() {
    triedCodes = new ArrayList<>();
    keyCodes = new ArrayList<>();
    codeGuessed = false;

    Peg[] pegs = new Peg[Row.PEG_COUNT];

    for (int i = 0; i < Row.PEG_COUNT; i++) {
      pegs[i] = new Peg(PlayableColor.getRandom());
    }

    hiddenRow = new Row(pegs);

    System.out.println(hiddenRow);
  }

  public ArrayList<Row> getTriedCodes() {
    return triedCodes;
  }

  public ArrayList<Row> getKeyCodes() {
    return keyCodes;
  }

  public Row getHiddenRow() {
    return hiddenRow;
  }

  public boolean isGameOver() {
    return codeGuessed || triedCodes.size() == TOTAL_TRIES;
  }

  public Integer getFinalScore() {
    if (!isGameOver()) {
      throw new RuntimeException("game is not over yet");
    }

    return TOTAL_TRIES - triedCodes.size();
  }

  Row tryAnswer(Row answer) {
    triedCodes.add(answer);

    Peg[] answerPegs = answer.getPegs();
    Peg[] hiddenRowPegs = hiddenRow.getPegs();
    Peg[] hint = new Peg[Row.PEG_COUNT];
    for (int i = 0; i < Row.PEG_COUNT; i++) {
      if (answerPegs[i].equals(hiddenRowPegs[i])) {
        hint[i] = new Peg(KeyColor.BLACK);
      } else if (Arrays.asList(hiddenRowPegs).contains(answerPegs[i])) {
        hint[i] = new Peg(KeyColor.WHITE);
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
}

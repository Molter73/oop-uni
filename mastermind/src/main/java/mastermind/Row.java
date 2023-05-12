package mastermind;

import java.util.Arrays;

public class Row {
  public static final int PEG_COUNT = 5;
  Peg[] pegs;

  public Row(Peg[] pegs) {
    if (pegs.length != PEG_COUNT) {
      throw new RuntimeException("invalid number of pegs: " + pegs.length);
    }

    this.pegs = pegs;
  }

  public Peg[] getPegs() {
    return pegs;
  }

  @Override
  public String toString() {
    return Arrays.toString(pegs);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Row)) {
      return false;
    }

    Row o = (Row) other;

    if (pegs.length != o.pegs.length) {
      return false;
    }

    for (int i = 0; i < PEG_COUNT; i++) {
      if (!pegs[i].equals(o.pegs[i])) {
        return false;
      }
    }

    return true;
  }
}

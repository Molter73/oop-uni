package mastermind;

/** A single row in a MasterMind board. */
public class Row {
  /** The amount of pegs that form a row. */
  public static final int PEG_COUNT = 5;

  Peg[] pegs;

  /**
   * Constructor for a row.
   *
   * <p>The constructor takes an array of {@link Peg}s, validates it is of the right length and
   * translates it into an immutable row.
   *
   * @param pegs An array of {@link Peg}s that will form the row.
   */
  public Row(Peg[] pegs) {
    if (pegs.length != PEG_COUNT) {
      throw new RuntimeException("invalid number of pegs: " + pegs.length);
    }

    this.pegs = pegs;
  }

  /**
   * Return the inner row of {@link Peg}s that form the row.
   *
   * @return An array of {@link Peg}s
   */
  public Peg[] getPegs() {
    return pegs;
  }

  /**
   * Transform the row into a string representation.
   *
   * @return a string representation of the row.
   */
  @Override
  public String toString() {
    String output = "|";

    for (Peg peg : pegs) {
      output += peg.toString() + "|";
    }

    return output;
  }

  /**
   * Compare two rows and return if they are the equivalent to each other.
   *
   * <p>Two rows are considered to be equal is their inner pegs are the same color in the same
   * positions.
   *
   * @param other The row that needs to be compared to this.
   * @return true if the rows are equal to each other, false otherwise.
   */
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

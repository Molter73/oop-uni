package mastermind;

/**
 * A representation of a peg in the MasterMind game.
 *
 * <p>Pegs only have an assigned, immutable {@link Color}
 */
public class Peg {
  Color color;

  /**
   * Construct a Peg, assigning it the provided color.
   *
   * @param color The color for the peg that is being created.
   */
  public Peg(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return color.getColor();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Peg)) {
      return false;
    }

    return this.color == ((Peg) other).color;
  }
}

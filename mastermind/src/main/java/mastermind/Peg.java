package mastermind;

public class Peg {
  Color color;

  public Peg(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
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

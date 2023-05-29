package mastermind;

/**
 * Pegs used for hinting the user on differences between their provided answer
 * and the hidden code.
 */
public enum KeyColor implements Color {
  WHITE {
    public String getColor() {
      return "White";
    }
  },
  BLACK {
    public String getColor() {
      return "Black";
    }
  },
  EMPTY {
    public String getColor() {
      return "Empty";
    }
  };
}

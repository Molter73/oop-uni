package mastermind;

/**
 * Pegs used for hinting the user on differences between their provided answer and the hidden code.
 */
public enum KeyColor implements Color {
  WHITE {
    public String getColor() {
      return useColors ? "\u001B[97m" + bigDot + "\u001B[0m" : "White ";
    }
  },
  BLACK {
    public String getColor() {
      return useColors ? "\u001B[90m" + bigDot + "\u001B[0m" : "Black ";
    }
  },
  EMPTY {
    public String getColor() {
      return useColors ? smallDot : "Empty ";
    }
  };
}

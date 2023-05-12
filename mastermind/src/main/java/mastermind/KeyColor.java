package mastermind;

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

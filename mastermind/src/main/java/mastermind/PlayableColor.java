package mastermind;

import java.util.Random;

public enum PlayableColor implements Color {
  RED {
    public String getColor() {
      return useColors ? "\u001B[91m" + bigDot + "\u001B[0m" : "Red   ";
    }
  },
  BLUE {
    public String getColor() {
      return useColors ? "\u001B[94m" + bigDot + "\u001B[0m" : "Blue  ";
    }
  },
  GREEN {
    public String getColor() {
      return useColors ? "\u001B[92m" + bigDot + "\u001B[0m" : "Green ";
    }
  },
  YELLOW {
    public String getColor() {
      return useColors ? "\u001B[93m" + bigDot + "\u001B[0m" : "Yellow";
    }
  },
  PINK {
    public String getColor() {
      return useColors ? "\u001B[95m" + bigDot + "\u001B[0m" : "Pink  ";
    }
  },
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
  BROWN {
    public String getColor() {
      return useColors ? "\u001B[96m" + bigDot + "\u001B[0m" : "Brown ";
    }
  };

  private static final Random RANDOM = new Random();
  private static final Color[] COLORS = PlayableColor.values();

  /**
   * Generate a random color.
   *
   * @return A {@link PlayableColor} picked at random.
   */
  public static Color getRandom() {
    int i = RANDOM.nextInt(COLORS.length);
    return COLORS[i];
  }
}

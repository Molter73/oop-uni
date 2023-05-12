package mastermind;

import java.util.Random;

public enum PlayableColor implements Color {
  RED {
    public String getColor() {
      return "Red";
    }
  },
  BLUE {
    public String getColor() {
      return "Blue";
    }
  },
  GREEN {
    public String getColor() {
      return "Green";
    }
  },
  YELLOW {
    public String getColor() {
      return "Yellow";
    }
  },
  PINK {
    public String getColor() {
      return "Pink";
    }
  },
  BROWN {
    public String getColor() {
      return "Brown";
    }
  };

  private static final Random RANDOM = new Random();
  private static final Color[] COLORS = PlayableColor.values();

  public static Color getRandom() {
    int i = RANDOM.nextInt(COLORS.length);
    return COLORS[i];
  }
}

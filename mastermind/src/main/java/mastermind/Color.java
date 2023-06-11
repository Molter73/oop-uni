package mastermind;

/**
 * Interface for colors.
 *
 * This allows multiple types of {@link Peg}s to be used for different purposes.
 */
public interface Color {
  public String getColor();

  static final String bigDot = "\u25CF";
  static final String smallDot = "\u2022";

  /**
   * Read the MASTERMIND_USE_COLORS environment variable and parse it as a
   * boolean.
   *
   * @return false if the MASTERMIND_USE_COLORS environment variable is set to
   *         "false", true otherwise.
   */
  static boolean parseUseColors() {
    String useColors = System.getenv("MASTERMIND_USE_COLORS");

    if (useColors == null) {
      return true;
    }

    return Boolean.parseBoolean(useColors);
  }

  static final boolean useColors = parseUseColors();
}

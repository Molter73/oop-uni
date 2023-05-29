package mastermind;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/** Unit test for simple App. */
public class RowTest {
  @Test
  public void testComparisons() {
    // Compare to itself
    Row row =
        new Row(
            new Peg[] {
              new Peg(PlayableColor.RED),
              new Peg(PlayableColor.BLUE),
              new Peg(PlayableColor.PINK),
              new Peg(PlayableColor.BROWN),
              new Peg(PlayableColor.GREEN),
            });

    assertTrue(row.equals(row));

    // Compare to other row with same values
    Row other =
        new Row(
            new Peg[] {
              new Peg(PlayableColor.BLUE),
              new Peg(PlayableColor.BLUE),
              new Peg(PlayableColor.PINK),
              new Peg(PlayableColor.BROWN),
              new Peg(PlayableColor.GREEN),
            });

    assertFalse(row.equals(other));
    //
    // Compare to other row with the same values
    other =
        new Row(
            new Peg[] {
              new Peg(PlayableColor.RED),
              new Peg(PlayableColor.BLUE),
              new Peg(PlayableColor.PINK),
              new Peg(PlayableColor.BROWN),
              new Peg(PlayableColor.GREEN),
            });

    assertTrue(row.equals(other));
  }
}

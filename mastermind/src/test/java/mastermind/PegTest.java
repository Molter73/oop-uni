package mastermind;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Unit test for simple App. */
public class PegTest {
  @Test
  public void testComparisons() {
    // Compare to itself
    Peg peg = new Peg(PlayableColor.RED);

    assertTrue(peg.equals(peg));

    // Comparing to other peg of different color
    Peg otherPeg = new Peg(KeyColor.BLACK);

    assertFalse(peg.equals(otherPeg));

    // Compare to other peg of the same color
    otherPeg = new Peg(PlayableColor.RED);

    assertTrue(peg.equals(otherPeg));
  }
}

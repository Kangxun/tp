package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_null_createsDefaultAge() {
        String noAge = "N.A";
        assertEquals(new Age(null).value, noAge);
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "Dinosaur";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // invalid ages
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("ME")); // Contains non numbers
        assertFalse(Age.isValidAge("2one3")); // Contains non numbers
        assertFalse(Age.isValidAge("12.5")); // Contains decimal point

        // valid ages
        assertTrue(Age.isValidAge("10"));
        assertTrue(Age.isValidAge("2000"));
        assertTrue(Age.isValidAge("N.A"));
    }
}

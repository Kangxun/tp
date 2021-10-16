package seedu.address.model.person.interests;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterestTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interest(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException1() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Interest(invalidTagName));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException2() {
        String invalidTagName = " ";
        assertThrows(IllegalArgumentException.class, () -> new Interest(invalidTagName));
    }

    @Test
    public void isValidInterest() {
        // null Interest
        assertThrows(NullPointerException.class, () -> Interest.isValidInterest(null));
    }
}

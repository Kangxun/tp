package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents whether a Person is called in the address book.
 */
public class IsDone {

    public static final String MESSAGE_CONSTRAINTS = "Done has to either be TRUE or FALSE";
    public final boolean value;

    /**
     * Constructs an {@code IsDone}.
     *
     * @param isDone Indication whether person is called.
     */
    public IsDone(String isDone) {
        requireNonNull(isDone);
        checkArgument(isValidIsDone(isDone), MESSAGE_CONSTRAINTS);
        value = isDone.toUpperCase(Locale.ROOT).equals("TRUE");
    }

    /**
     * Returns true if a given string is a valid IsDone.
     */
    public static boolean isValidIsDone(String test) {
        String testValid = test.toUpperCase(Locale.ROOT);
        return testValid.equals("TRUE") || testValid.equals("FALSE") || testValid.equals("");
    }

    @Override
    public String toString() {
        if (value) {
            return "True";
        }
        return "False";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsDone // instanceof handles nulls
                && (value == ((IsDone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        if (value) {
            return 1;
        }
        return 0;
    }
}

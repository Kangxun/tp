package seedu.address.model.person.interests;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Interest {
    public static final String MESSAGE_CONSTRAINTS = "Interest can take any value, but should not be an empty string";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Interests}.
     *
     * @param interest Indication whether person is called.
     */
    public Interest(String interest) {
        requireNonNull(interest);
        checkArgument(isValidInterest(interest), MESSAGE_CONSTRAINTS);
        value = interest;
    }

    /**
     * Returns true if a given string is a valid interest.
     */
    public static boolean isValidInterest(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interest // instanceof handles nulls
                && value.equals(((Interest) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

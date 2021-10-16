package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Age {
    public static final String MESSAGE_CONSTRAINTS = "Age should only contain numbers.";
    public static final String NO_AGE = "N.A";

    // Age should only contain numbers
    public static final String VALIDATION_REGEX = "[0-9]+";

    public final String value;

    /**
     * Constructs an {@code Age}.
     * If no age is provided(i.e. @param age = null), default value of 'N.A' will be given
     * @param age A valid age.
     */
    public Age(String age) {
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        value = Objects.requireNonNullElse(age, NO_AGE);
    }

    /**
     * Returns true if a given string is a valid age.
     */
    public static boolean isValidAge(String test) {
        if (test == null) {
            return true;
        }
        if (test.equals(NO_AGE)) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if there is a proper value.
     * @return True if value equals to "N.A", false otherwise.
     */
    public boolean isEmpty() {
        return value.equals(NO_AGE);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Age // instanceof handles nulls
                && value.equals(((Age) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

public class Gender {
    public static final String MESSAGE_CONSTRAINTS = "Gender can only be 'M' or 'F'";
    public static final String NO_GENDER = "N.A";

    public static final String VALIDATION_REGEX = "M|F|N.A";

    public final String value;

    /**
     * Constructs an {@code Gender}.
     * If no gender is provided(i.e. @param address = null), default value of 'N.A' will be given
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        if (gender == null) {
            value = NO_GENDER;
        } else {
            value = gender.toUpperCase(Locale.ROOT);
        }
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static Boolean isValidGender(String gender) {
        if (gender == null) {
            return true;
        }
        String test = gender.toUpperCase(Locale.ROOT);
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if there is a proper value.
     * @return True if value equals to "N.A", false otherwise.
     */
    public boolean isEmpty() {
        return value.equals(NO_GENDER);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

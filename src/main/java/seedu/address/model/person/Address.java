package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, but should not be an empty string";
    public static final String NO_ADDRESS = "N.A";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     * If no address is provided(i.e. @param address = null), default value of 'N.A' will be given
     * @param address A valid address.
     */
    public Address(String address) {
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = Objects.requireNonNullElse(address, NO_ADDRESS);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        if (test == null) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if there is a proper value.
     * @return True if value equals to "N.A", false otherwise.
     */
    public boolean isEmpty() {
        return value.equals(NO_ADDRESS);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

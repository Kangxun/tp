package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.interests.Interest;

public class JsonAdaptedInterest {
    private final String interest;

    /**
     * Constructs a {@code JsonAdaptedInterest} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedInterest(String interest) {
        this.interest = interest;
    }

    /**
     * Converts a given {@code Interest} into this class for Jackson use.
     */
    public JsonAdaptedInterest(Interest source) {
        interest = source.value;
    }

    @JsonValue
    public String getInterest() {
        return interest;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Interest} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interest.
     */
    public Interest toModelType() throws IllegalValueException {
        if (!Interest.isValidInterest(interest)) {
            throw new IllegalValueException(Interest.MESSAGE_CONSTRAINTS);
        }
        return new Interest(interest);
    }
}

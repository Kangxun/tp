package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.interests.Interest;
import seedu.address.testutil.PersonBuilder;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = "";
    private static final String INVALID_GENDER = "APACHE HELICOPTER";
    private static final String INVALID_AGE = "Eighteen";
    private static final String INVALID_INTEREST = "";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ISDONE = BENSON.getIsDone().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();
    private static final String VALID_INTEREST_1 = BENSON.getInterests().getInterest(Index.fromZeroBased(0)).value;
    private static final String VALID_INTEREST_2 = BENSON.getInterests().getInterest(Index.fromZeroBased(1)).value;
    private static final List<JsonAdaptedInterest> VALID_INTERESTS = BENSON.getInterests().getAllInterests().stream()
            .map(JsonAdaptedInterest::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                        VALID_ADDRESS, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                VALID_ADDRESS, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                        VALID_ADDRESS, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ISDONE,
                VALID_ADDRESS, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ISDONE,
                        VALID_ADDRESS, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ISDONE,
                VALID_ADDRESS, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                        INVALID_ADDRESS, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_isValid() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                null, VALID_GENDER, VALID_AGE, VALID_INTERESTS);
        Person personWithNullAddress = new PersonBuilder().withName(VALID_NAME).withPhone(VALID_PHONE)
                .withEmail(VALID_EMAIL).withDone(VALID_ISDONE).withAddress(null).withGender(VALID_GENDER)
                .withAge(VALID_AGE).withInterest(VALID_INTEREST_1, VALID_INTEREST_2).build();
        assertEquals(person.toModelType(), personWithNullAddress);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                        VALID_ADDRESS, INVALID_GENDER, VALID_AGE, VALID_INTERESTS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_isValid() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                VALID_ADDRESS, null, VALID_AGE, VALID_INTERESTS);
        Person personWithNullGender = new PersonBuilder().withName(VALID_NAME).withPhone(VALID_PHONE)
                .withEmail(VALID_EMAIL).withDone(VALID_ISDONE).withAddress(VALID_ADDRESS).withGender(null)
                .withAge(VALID_AGE).withInterest(VALID_INTEREST_1, VALID_INTEREST_2).build();
        assertEquals(person.toModelType(), personWithNullGender);
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                        VALID_ADDRESS, VALID_GENDER, INVALID_AGE, VALID_INTERESTS);
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAge_isValid() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                VALID_ADDRESS, VALID_GENDER, null, VALID_INTERESTS);
        Person personWithNullAge = new PersonBuilder().withName(VALID_NAME).withPhone(VALID_PHONE)
                .withEmail(VALID_EMAIL).withDone(VALID_ISDONE).withAddress(VALID_ADDRESS).withGender(VALID_GENDER)
                .withAge(null).withInterest(VALID_INTEREST_1, VALID_INTEREST_2).build();
        assertEquals(person.toModelType(), personWithNullAge);
    }

    @Test
    public void toModelType_invalidInterest_throwsIllegalValueException() {
        List<JsonAdaptedInterest> invalidInterests = new ArrayList<>(VALID_INTERESTS);
        invalidInterests.add(new JsonAdaptedInterest(INVALID_INTEREST));

        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                        VALID_ADDRESS, VALID_GENDER, VALID_AGE, invalidInterests);
        String expectedMessage = Interest.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullInterests_isValid() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ISDONE,
                VALID_ADDRESS, VALID_GENDER, VALID_AGE, null);
        Person personWithNullInterests = new PersonBuilder().withName(VALID_NAME).withPhone(VALID_PHONE)
                .withEmail(VALID_EMAIL).withDone(VALID_ISDONE).withAddress(VALID_ADDRESS).withGender(VALID_GENDER)
                .withAge(VALID_AGE).build();
        assertEquals(person.toModelType(), personWithNullInterests);
    }
}

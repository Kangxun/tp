package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.IsDone;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.interests.Interest;
import seedu.address.model.person.interests.InterestsList;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_DONE = "false";
    public static final String DEFAULT_ADDRESS = null;
    public static final String DEFAULT_GENDER = null;
    public static final String DEFAULT_AGE = null;

    private Name name;
    private Phone phone;
    private Email email;
    private IsDone isDone;
    private Address address;
    private Gender gender;
    private Age age;
    private InterestsList interests;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        isDone = new IsDone(DEFAULT_DONE);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        age = new Age(DEFAULT_AGE);
        interests = new InterestsList();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        isDone = personToCopy.getIsDone();
        address = personToCopy.getAddress();
        gender = personToCopy.getGender();
        age = personToCopy.getAge();
        interests = personToCopy.getInterests();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Person} that we are building.
     */
    public PersonBuilder withDone(String isDone) {
        this.isDone = new IsDone(isDone);
        return this;
    }

    /**
     * Sets the {@code address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code age} of the {@code Person} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code interest} of the {@code Person} that we are building.
     */
    public PersonBuilder withInterest(String ...interestList) {
        for (String interest : interestList) {
            Interest i = new Interest(interest);
            interests.addInterest(i);
        }
        return this;
    }

    /**
     * Builds person
     * @return the person
     */
    public Person build() {
        return new Person(name, phone, email, isDone, address, gender, age, interests);
    }

}

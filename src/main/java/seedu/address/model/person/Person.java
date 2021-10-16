package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.interests.InterestsList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Gender gender;
    private final Age age;
    private final InterestsList interests;

    // Data fields
    private final IsDone isDone;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, IsDone isDone,
                  Address address, Gender gender, Age age, InterestsList interests) {
        requireAllNonNull(name, phone, email, isDone, address, gender, age);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isDone = isDone;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.interests = interests;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public IsDone getIsDone() {
        return isDone;
    }

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public Age getAge() {
        return age;
    }

    public InterestsList getInterests() {
        return interests;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getAge().equals(getAge())
                && otherPerson.getInterests().equals(getInterests());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Done: ")
                .append(getIsDone());
        if (!address.isEmpty()) {
            builder.append("; Address: ").append(getAddress());
        }
        if (!gender.isEmpty()) {
            builder.append("; Gender: ").append(getGender());
        }
        if (!age.isEmpty()) {
            builder.append("; Age: ").append(getAge());
        }
        if (!interests.isEmpty()) {
            builder.append("; Interests: ").append(getInterests());
        }
        return builder.toString();
    }

}

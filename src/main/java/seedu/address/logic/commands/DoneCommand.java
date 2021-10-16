package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.IsDone;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.interests.InterestsList;


/**
 * Changes the done field of an existing person in the address book.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the 'Called' field of the address identified "
            + "by the index number of the address listing. "
            + "Existing 'Called' field will change to True.\n"
            + "Parameter: INDEX (must be a positive integer)";

    public static final String MESSAGE_DONE_CALL_SUCCESS = "Marked Call as Done: %1$s";
    public static final String MESSAGE_ALREADY_DONE_CALL = "The 'Called' field for the specified address listing is "
            + "already marked as true!";

    private final Index index;

    /**
     * Creates a DoneCommand to mark the specified {@code Person} as done
     *
     * @param index of the person in the person list
     */
    public DoneCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (personToEdit.getIsDone().value) {
            return new CommandResult(MESSAGE_ALREADY_DONE_CALL);
        }

        Person editedPerson = createCalledPerson(personToEdit);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DONE_CALL_SUCCESS, editedPerson));
    }

    private static Person createCalledPerson(Person personToEdit) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        IsDone updatedIsDone = new IsDone("TRUE");
        Address address = personToEdit.getAddress();
        Gender gender = personToEdit.getGender();
        Age age = personToEdit.getAge();
        InterestsList interests = personToEdit.getInterests();

        return new Person(name, phone, email, updatedIsDone, address, gender, age, interests);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DoneCommand)) {
            return false;
        }

        DoneCommand e = (DoneCommand) other;
        return index.equals(e.index);
    }
}

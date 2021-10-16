package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

public class FullPersonCard extends UiPart<Region> {

    private static final String FXML = "FullPersonCard.fxml";

    private static final String PHONE_LABEL = "Phone: ";
    private static final String EMAIL_LABEL = "Email: ";
    private static final String DONE_LABEL = "Called: ";
    private static final String ADDRESS_LABEL = "Address: ";
    private static final String GENDER_LABEL = "Gender: ";
    private static final String AGE_LABEL = "Age: ";
    private static final String INTERESTS_LABEL = "Interests: ";
    private static final String DEFAULT_FIELD = "N.A";
    private static final String NO_PERSON_TO_DISPLAY = "No person to display";

    private final Logger logger = LogsCenter.getLogger(FullPersonCard.class);

    private Person person;

    @FXML
    private ScrollPane fullPersonDetails;
    @FXML
    private VBox fullPersonContainer;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label isDone;
    @FXML
    private Label address;
    @FXML
    private Label gender;
    @FXML
    private Label age;
    @FXML
    private Label interests;

    /**
     * Constructor for the FullPersonCard
     * @param persons The current list displayed
     * @param displayedIndex The index of the person to display on the card
     * @param windowWidth The size of the card
     */
    public FullPersonCard(List<Person> persons, int displayedIndex, double windowWidth) {
        super(FXML);

        fullPersonDetails.setPrefWidth(windowWidth / 3);
        //cardPane.setPrefWidth(windowWidth / 3);
        try {
            this.person = persons.get(displayedIndex - 1);
            id.setText(displayedIndex + ". ");
            name.setText(person.getName().fullName);
            phone.setText(person.getPhone().value);
            email.setText(person.getEmail().value);

            if (person.getIsDone().value) {
                isDone.setText("True");
                isDone.setStyle("-fx-text-fill: springgreen");
            } else {
                isDone.setText("False");
                isDone.setStyle("-fx-text-fill: red");
            }

            address.setText(person.getAddress().value);
            gender.setText(person.getGender().value);
            age.setText(DEFAULT_FIELD);
            interests.setText(person.getInterests().toString());
        } catch (IndexOutOfBoundsException e) {
            createEmptyCard();
        }
    }

    /**
     * Creates an empty card where all the fields are N.A.
     */
    private void createEmptyCard() {
        this.person = null;
        id.setText(NO_PERSON_TO_DISPLAY);
        name.setText("");
        phone.setText(DEFAULT_FIELD);
        email.setText(DEFAULT_FIELD);
        isDone.setText(DEFAULT_FIELD);
        address.setText(DEFAULT_FIELD);
        address.setText(DEFAULT_FIELD);
        gender.setText(DEFAULT_FIELD);
        age.setText(DEFAULT_FIELD);
        interests.setText(DEFAULT_FIELD);
    }

}

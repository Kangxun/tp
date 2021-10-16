package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.storage.CsvAdaptedPerson;

/**
 * Converts a Java object instance to CSV and vice versa
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);
    private static List<Integer> unsuccessfulRow = new ArrayList<>();
    private static String header = "";

    /**
     * Reads from file and returns a list of valid people to be imported in
     *
     * @param filePath path that contains the csv file to be imported
     * @return list of valid people to be imported
     * @throws IOException thrown when file util could not read the file
     */
    public static List<Person> deserializeObjectFromCsvFile(Path filePath)
            throws IOException {
        requireNonNull(filePath);
        return fromCsvString(FileUtil.readFromFile(filePath));
    }

    /**
     * Reads from CSV file and converts into an optional list of people
     *
     * @param filePath filepath of the csv
     * @return list optional, whether there is a list of people within the csv
     * @throws DataConversionException
     */
    public static Optional<List<Person>> readCsvFile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        if (!Files.exists(filePath)) {
            logger.info("CSV file " + filePath + " not found");
            return Optional.empty();
        }

        List<Person> persons;

        try {
            persons = deserializeObjectFromCsvFile(filePath);
        } catch (IOException e) {
            logger.warning("Error reading from jsonFile file " + filePath + ": " + e);
            throw new DataConversionException(e);
        }

        return Optional.of(persons);
    }

    /**
     * Returns the list of valid person from the given file or {@code Optional.empty()} object if the file is not found.
     *   If any columns in the CSV file is not found, it will not be imported
     *     Will not read the header of the file as it does not contain a persondetails
     * @param csv
     * @return
     */
    static List<Person> fromCsvString(String csv) {
        unsuccessfulRow = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        String[] personRows = csv.split("\n");

        header = personRows[0];
        // Skips the header row and starts from the second row
        for (int i = 1; i < personRows.length; i++) {
            Optional<Person> temp = createPerson(personRows[i].trim(), i + 1);
            if (temp.equals(Optional.empty())) {
                unsuccessfulRow.add(i + 1);
                continue;
            }
            persons.add(temp.get());
        }
        return persons;
    }

    /**
     * Creates a valid person based on the rowdetails found in the csv file
     *
     * @param rowStringPerson
     * @return
     */
    static Optional<Person> createPerson(String rowStringPerson, int rowNumber) {
        try {
            return Optional.of(new CsvAdaptedPerson(rowStringPerson).toModelType());
        } catch (IllegalValueException e) {
            logger.warning("CSV File Import error : In row " + rowNumber + " : " + e);
            return Optional.empty();
        }
    }

    public static String getUnsuccessfulRow() {
        return unsuccessfulRow.toString();
    }

}

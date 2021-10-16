package seedu.address.model.person.interests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class InterestsListTest {

    private InterestsList interestsList = new InterestsList();

    @Test
    public void addInterest_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> interestsList.addInterest(null));
    }

    @Test
    public void duplicateInterests_throwsIllegalArgumentException() {
        Interest i = new Interest("Jumping");
        interestsList.addInterest(i);
        assertThrows(IllegalArgumentException.class, () -> interestsList.addInterest(i));
    }

    @Test
    public void getInterestTest() {
        Interest i = new Interest("Flying");
        interestsList.addInterest(i);
        assertEquals(i, interestsList.getInterest(Index.fromZeroBased(0)));
    }

    @Test
    public void equalsTest() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");

        interestsList.addInterest(i1);
        interestsList.addInterest(i2);
        interestsList.addInterest(i3);

        InterestsList otherList = new InterestsList();
        otherList.addInterest(i1);
        otherList.addInterest(i2);
        otherList.addInterest(i3);

        assertEquals(interestsList, otherList);
    }
}

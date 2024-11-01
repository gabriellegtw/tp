package seedu.address.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;


/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class PersonCount extends UiPart<Region> {

    private static final String FXML = "PersonCount.fxml";
    private static final String DISPLAY_FORMAT = "Currently displaying %d of %d Students.";
    private ReadOnlyAddressBook addressBook;

    @javafx.fxml.FXML
    private Label personCountDisplay;

    /**
     * Creates a {@code PersonCount} with the given {@code AddressBook} and {@code filteredPersonList}.
     */
    public PersonCount(ObservableList<Person> filteredPersonList, ReadOnlyAddressBook addressBook) {
        super(FXML);
        this.addressBook = addressBook;

        personCountDisplay.setText(String.format(DISPLAY_FORMAT, filteredPersonList.size(),
                addressBook.getPersonList().size()));

        filteredPersonList.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Person> c) {
                if (c.next()) {
                    int displayNum = c.getList().size();
                    int totalNum = addressBook.getPersonList().size();

                    personCountDisplay.setText(String.format(DISPLAY_FORMAT, displayNum, totalNum));
                }
            }
        });

    }

}

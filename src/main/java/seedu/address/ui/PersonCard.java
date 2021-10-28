package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.storage.ImageStorage;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label nationality;
    @FXML
    private Label email;
    @FXML
    private Label tutorialGroup;
    @FXML
    private Label gender;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane socialHandles;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        Image genderImage = ImageStorage.getGenderIcon(person.getGender().gender);
        if (genderImage != null) {
            ImageView genderIcon = new ImageView(genderImage);
            genderIcon.setFitHeight(15);
            genderIcon.setFitWidth(15);
            name.setGraphic(genderIcon);
        }
        phone.setText(person.getPhone().value);
        if (person.getPhone().value.isEmpty()) {
            phone.setManaged(false);
        }
        nationality.setText(person.getNationality().value);
        if (person.getNationality().value.isEmpty()) {
            nationality.setManaged(false);
        }
        email.setText(person.getEmail().value);
        if (person.getEmail().value.isEmpty()) {
            email.setManaged(false);
        }
        tutorialGroup.setText(person.getTutorialGroup().value);
        if (person.getTutorialGroup().value.isEmpty()) {
            tutorialGroup.setManaged(false);
        }
        remark.setText(person.getRemark().value);
        if (person.getRemark().value.isEmpty()) {
            remark.setManaged(false);
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label l = new Label(tag.tagName);
                    l.setStyle("-fx-background-color: " + tag.tagColour + ";");
                    tags.getChildren().add(l);
                });
        person.getSocialHandles().stream()
                .sorted(Comparator.comparing(socialHandle -> socialHandle.platform))
                .forEach(socialHandle -> {
                    Label label = new Label();
                    Image image = ImageStorage.getSocialIcon(socialHandle.platform);
                    if (image == null) {
                        label.setText(socialHandle.platform + " : " + socialHandle.value);
                    } else {
                        ImageView platformIcon = new ImageView(image);
                        platformIcon.setFitHeight(20);
                        platformIcon.setFitWidth(20);
                        label.setGraphic(platformIcon);
                        label.setText(socialHandle.value);
                    }
                    socialHandles.getChildren().add(label);
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

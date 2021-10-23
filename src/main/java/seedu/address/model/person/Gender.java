package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.image.Image;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be M (for male), F (for female) and O (for others), and it should not be blank";

    /*
     * The gender must be one of the following character: F, M or O.
     */
    public static final String VALIDATION_REGEX = "[FMO]";
    private static final Path GENDER_ICON_FOLDER = Paths.get("src", "main", "resources", "images", "gender");


    public final String gender;
    public final Image genderIcon;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.gender = gender;
        this.genderIcon = parseGender();
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        if (test.isEmpty()) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the gender is a male.
     */
    public boolean isMale() {
        return gender.equals("M");
    }

    /**
     * Returns true if the gender is a female.
     */
    public boolean isFemale() {
        return gender.equals("F");
    }

    /**
     * Returns true if the gender is others.
     */
    public boolean isOther() {
        return gender.equals("O");
    }

    /**
     * Returns a gender icon given a gender.
     */
    public Image parseGender() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/main/resources/images/gender/Others.png");
            if (isMale()) {
                inputStream = new FileInputStream("src/main/resources/images/gender/Male.png");
            } else if (isFemale()) {
                inputStream = new FileInputStream("src/main/resources/images/gender/Female.png");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!");
        }
        return new Image(inputStream);
    }

    @Override
    public String toString() {
        return gender;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && gender.equals(((Gender) other).gender)); // state check
    }

    @Override
    public int hashCode() {
        return gender.hashCode();
    }

}

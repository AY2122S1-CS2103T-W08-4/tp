package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag names should be alphanumeric";
    public static final String MESSAGE_CONSTRAINTS_COLOURS = "Tag Colour should follow the Colour Code format."
            + " E.g. Blue = #0000FF";
    public static final String DEFAULT_COLOUR_CODE = "#3e7b91";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public static final String COLOUR_VALIDATION_REGEX = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";


    public final String tagName;
    public final String tagColour;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.tagColour = DEFAULT_COLOUR_CODE;
        String assertTagColour = tagColour;
        assert assertTagColour == "#3e7b91" : "Default tag colour code should be #3e7b91";
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name
     * @param colour  A valid tag colour
     */
    public Tag(String tagName, String colour) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        requireNonNull(colour);
        checkArgument(isValidTagColour(colour), MESSAGE_CONSTRAINTS_COLOURS);
        this.tagName = tagName;
        this.tagColour = colour;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid tag colour.
     */
    public static boolean isValidTagColour(String test) {
        return test.matches(COLOUR_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ", " + tagColour + ']';
    }

    public String getTagName() {
        return tagName;
    }

}

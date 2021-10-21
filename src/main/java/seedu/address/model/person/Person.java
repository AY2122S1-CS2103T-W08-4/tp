package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.SpecialTag;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Nationality nationality;
    private final TutorialGroup tutorialGroup;
    private final SocialHandle socialHandle;
    private final Gender gender;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<SpecialTag> specialTags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nationality nationality,
                  TutorialGroup tutorialGroup, SocialHandle socialHandle, Gender gender,
                  Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, nationality, tutorialGroup, socialHandle, gender, remark, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.tutorialGroup = tutorialGroup;
        this.socialHandle = socialHandle;
        this.gender = gender;
        this.remark = remark;
        this.tags.addAll(tags);
    }

    public Person(Name name, Phone phone, Email email, Nationality nationality,
                  TutorialGroup tutorialGroup, SocialHandle socialHandle, Gender gender,
                  Remark remark, Set<Tag> tags, Set<SpecialTag> specialTags) {
        requireAllNonNull(name, phone, email, nationality, tutorialGroup, socialHandle, gender, remark, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.tutorialGroup = tutorialGroup;
        this.socialHandle = socialHandle;
        this.gender = gender;
        this.remark = remark;
        this.tags.addAll(tags);
        this.specialTags.addAll(specialTags);
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

    public Nationality getNationality() {
        return nationality;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public SocialHandle getSocialHandle() {
        return socialHandle;
    }

    public Gender getGender() {
        return gender;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Set<SpecialTag> getSpecialTags() {
        return Collections.unmodifiableSet(specialTags);
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
                && otherPerson.getNationality().equals(getNationality())
                && otherPerson.getTutorialGroup().equals(getTutorialGroup())
                && otherPerson.getSocialHandle().equals(getSocialHandle())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getSpecialTags().equals(getSpecialTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nationality, tutorialGroup, socialHandle,
                gender, remark, tags, specialTags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Gender: ")
                .append(getGender())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Nationality: ")
                .append(getNationality())
                .append("; Tutorial Group: ")
                .append(getTutorialGroup())
                .append("; Social handle: ")
                .append(getSocialHandle())
                .append("; Remark: ")
                .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}

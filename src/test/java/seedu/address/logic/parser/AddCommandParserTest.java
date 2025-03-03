package seedu.address.logic.parser;

import static seedu.address.logic.commands.AddCommand.MESSAGE_INVALID_COMMAND_FORMAT_NAME_ABSENT;
import static seedu.address.logic.commands.AddCommand.MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SOCIAL_HANDLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_COLOUR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NATIONALITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NATIONALITY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SOCIAL_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SOCIAL_HANDLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NATIONALITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SOCIAL_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + GENDER_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple nationalities - last nationality accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_AMY
                + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tutorial groups - last tutorial group accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_AMY
                + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_AMY + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple social handles - last social handle accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_AMY + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple genders - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + GENDER_DESC_AMY + GENDER_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_AMY + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND + GENDER_DESC_BOB, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        //only name
        Person expectedPersonOnlyName = new PersonBuilder(AMY).withPhone("").withGender("")
                .withEmail("").withNationality("").withTutorialGroup("")
                .withRemark("").withTags().withSocialHandles().build();
        assertParseSuccess(parser, NAME_DESC_AMY, new AddCommand(expectedPersonOnlyName));

        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NATIONALITY_DESC_AMY
                + TUTORIAL_GROUP_DESC_AMY + SOCIAL_HANDLE_DESC_AMY + REMARK_DESC_AMY + GENDER_DESC_AMY,
                new AddCommand(expectedPerson));

        // no remark
        Person expectedPersonNoRemark = new PersonBuilder(AMY).withRemark("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NATIONALITY_DESC_AMY
                + TUTORIAL_GROUP_DESC_AMY + SOCIAL_HANDLE_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_FRIEND, new AddCommand(expectedPersonNoRemark));

        //no tutorial group
        Person expectedPersonNoTutorial = new PersonBuilder(AMY).withTutorialGroup("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NATIONALITY_DESC_AMY
                + SOCIAL_HANDLE_DESC_AMY + REMARK_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_FRIEND, new AddCommand(expectedPersonNoTutorial));

        //no tutorial group and remark
        Person expectedPersonNoTutorialNoRemark = new PersonBuilder(AMY).withTutorialGroup("")
                .withRemark("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NATIONALITY_DESC_AMY
                + SOCIAL_HANDLE_DESC_AMY + TAG_DESC_FRIEND + GENDER_DESC_AMY,
                new AddCommand(expectedPersonNoTutorialNoRemark));

        //no nationality
        Person expectedPersonNoNat = new PersonBuilder(AMY).withNationality("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TUTORIAL_GROUP_DESC_AMY + SOCIAL_HANDLE_DESC_AMY + REMARK_DESC_AMY
                + TAG_DESC_FRIEND + GENDER_DESC_AMY, new AddCommand(expectedPersonNoNat));

        //no email
        Person expectedPersonNoEmail = new PersonBuilder(AMY).withEmail("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + NATIONALITY_DESC_AMY
                + TUTORIAL_GROUP_DESC_AMY + SOCIAL_HANDLE_DESC_AMY + GENDER_DESC_AMY
                + REMARK_DESC_AMY + TAG_DESC_FRIEND, new AddCommand(expectedPersonNoEmail));

        //no email and tutorial group
        Person expectedPersonNoEmailNoTutorial = new PersonBuilder(AMY).withEmail("")
                .withTutorialGroup("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + NATIONALITY_DESC_AMY
                + SOCIAL_HANDLE_DESC_AMY + GENDER_DESC_AMY
                + REMARK_DESC_AMY + TAG_DESC_FRIEND, new AddCommand(expectedPersonNoEmailNoTutorial));

        //no phone
        Person expectedPersonNoPhone = new PersonBuilder(AMY).withPhone("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + NATIONALITY_DESC_AMY
                + TUTORIAL_GROUP_DESC_AMY + SOCIAL_HANDLE_DESC_AMY + GENDER_DESC_AMY
                + REMARK_DESC_AMY + TAG_DESC_FRIEND, new AddCommand(expectedPersonNoPhone));

        //no phone and remark
        Person expectedPersonNoPhoneNoRemark = new PersonBuilder(AMY).withPhone("").withRemark("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + NATIONALITY_DESC_AMY
                + TUTORIAL_GROUP_DESC_AMY + SOCIAL_HANDLE_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_FRIEND, new AddCommand(expectedPersonNoPhoneNoRemark));

        //no phone, email, and remark
        Person expectedPersonNoPhoneNoRemarkNoEmail = new PersonBuilder(AMY).withPhone("").withRemark("")
                .withEmail("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + NATIONALITY_DESC_AMY
                + TUTORIAL_GROUP_DESC_AMY + SOCIAL_HANDLE_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_FRIEND, new AddCommand(expectedPersonNoPhoneNoRemarkNoEmail));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT_NAME_ABSENT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB + GENDER_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_GENDER_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_NATIONALITY_BOB + VALID_TUTORIAL_GROUP_BOB + VALID_SOCIAL_HANDLE_BOB + VALID_REMARK_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GENDER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_GENDER_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Gender.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GENDER_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GENDER_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid tutorial group
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + INVALID_TUTORIAL_GROUP_DESC + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GENDER_DESC_BOB, TutorialGroup.MESSAGE_CONSTRAINTS);

        // invalid social handle
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + INVALID_SOCIAL_HANDLE_DESC + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GENDER_DESC_BOB, SocialHandle.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + REMARK_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + GENDER_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid tag colour
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NATIONALITY_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + REMARK_DESC_BOB
                + INVALID_TAG_COLOUR_DESC + VALID_TAG_FRIEND + GENDER_DESC_BOB, Tag.MESSAGE_CONSTRAINTS_COLOURS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + NATIONALITY_DESC_BOB + INVALID_TUTORIAL_GROUP_DESC + SOCIAL_HANDLE_DESC_BOB
                        + REMARK_DESC_BOB + GENDER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB
                        + REMARK_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GENDER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT, AddCommand.MESSAGE_USAGE));
    }
}

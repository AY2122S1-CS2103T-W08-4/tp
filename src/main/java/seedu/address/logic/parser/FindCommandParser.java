package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GenderContainsKeywordsPredicate;
import seedu.address.model.person.MultiplePredicates;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NationalityContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.SocialHandleContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.TutorialGroupContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NATIONALITY,
                        PREFIX_TUTORIAL_GROUP, PREFIX_SOCIAL_HANDLE, PREFIX_REMARK, PREFIX_TAG);

        ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()
            && !argMultimap.getValue(PREFIX_NAME).get().trim().isEmpty()) {
            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            int numOfEmptyValue = nameKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()
            && !argMultimap.getValue(PREFIX_PHONE).get().trim().isEmpty()) {
            List<String> phoneKeywords = argMultimap.getAllValues(PREFIX_PHONE);
            int numOfEmptyValue = phoneKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new PhoneContainsKeywordsPredicate(phoneKeywords));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()
            && !argMultimap.getValue(PREFIX_EMAIL).get().trim().isEmpty()) {
            List<String> emailKeywords = argMultimap.getAllValues(PREFIX_EMAIL);
            int numOfEmptyValue = emailKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new EmailContainsKeywordsPredicate(emailKeywords));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()
                && !argMultimap.getValue(PREFIX_GENDER).get().trim().isEmpty()) {
            List<String> genderKeywords = argMultimap.getAllValues(PREFIX_GENDER);
            int numOfEmptyValue = genderKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new GenderContainsKeywordsPredicate(genderKeywords));
        }
        if (argMultimap.getValue(PREFIX_NATIONALITY).isPresent()
            && !argMultimap.getValue(PREFIX_NATIONALITY).get().trim().isEmpty()) {
            List<String> nationalityKeywords = argMultimap.getAllValues(PREFIX_NATIONALITY);
            int numOfEmptyValue = nationalityKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new NationalityContainsKeywordsPredicate(nationalityKeywords));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).isPresent()
            && !argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get().trim().isEmpty()) {
            List<String> tutorialGroupKeywords = argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP);
            int numOfEmptyValue = tutorialGroupKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new TutorialGroupContainsKeywordsPredicate(tutorialGroupKeywords));
        }
        if (argMultimap.getValue(PREFIX_SOCIAL_HANDLE).isPresent()
                && !argMultimap.getValue(PREFIX_SOCIAL_HANDLE).get().trim().isEmpty()) {
            List<String> socialHandleKeywords = argMultimap.getAllValues(PREFIX_SOCIAL_HANDLE);
            int numOfEmptyValue = socialHandleKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new SocialHandleContainsKeywordsPredicate(socialHandleKeywords));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()
                && !argMultimap.getValue(PREFIX_REMARK).get().trim().isEmpty()) {
            List<String> remarkKeywords = argMultimap.getAllValues(PREFIX_REMARK);
            int numOfEmptyValue = remarkKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new RemarkContainsKeywordsPredicate(remarkKeywords));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()
            && !argMultimap.getValue(PREFIX_TAG).get().trim().isEmpty()) {
            List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
            int numOfEmptyValue = tagKeywords.stream()
                    .filter(item-> !item.isEmpty()).collect(Collectors.toList()).size();
            if (numOfEmptyValue != 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicateList.add(new TagContainsKeywordsPredicate(tagKeywords));
        }

        switch (predicateList.size()) {
        case (0):
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        case (1):
            return new FindCommand(predicateList.get(0));
        default:
            MultiplePredicates predicate = new MultiplePredicates(predicateList);
            return new FindCommand(predicate);
        }
    }
}


package seedu.address.logic.parser;

import seedu.address.logic.commands.StatisticsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TutorialGroup;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class StatisticsCommandParser implements Parser<StatisticsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatisticsCommand
     * and returns a StatisticsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatisticsCommand parse(String args) throws ParseException {
        TutorialGroup tutorialGroup = ParserUtil.parseTutorialGroup(args.trim());
        return new StatisticsCommand(tutorialGroup);
    }

}

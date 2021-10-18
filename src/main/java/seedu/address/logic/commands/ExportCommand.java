package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonSerializableAddressBook;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

/**
 * Imports a JSON file to the address book.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports a file identified by the given filename.\n"
            + "Parameters: FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " class.json";

    public static final String MESSAGE_SUCCESS = "File successfully exported as JSON format to %s";
    public static final String MESSAGE_IO_ERROR =
            "Problem while writing to the file. Please try again";

    private final Path outputFilePath; // fileName.json

    /**
     * Creates an ExportCommand to export the current data to the specified {@code outputFileName}
     */
    public ExportCommand(String outputFileName) {
        requireNonNull(outputFileName);
        this.outputFilePath = Paths.get("data", outputFileName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(model.getAddressBook());
        requireNonNull(outputFilePath);

        try {
            FileUtil.createIfMissing(outputFilePath);
            JsonUtil.saveJsonFile(new JsonSerializableAddressBook(model.getAddressBook()), outputFilePath);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_IO_ERROR);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, outputFilePath));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && outputFilePath.equals(((ExportCommand) other).outputFilePath));
    }
}

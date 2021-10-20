package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class RemarkContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public RemarkContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getRemark().value.toLowerCase()
                        .contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RemarkContainsKeywordsPredicate) other).keywords)); // state check
    }

}

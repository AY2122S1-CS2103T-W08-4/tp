package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Social Handle} matches any of the keywords given.
 */
public class SocialHandleContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public SocialHandleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getSocialHandle().value.toLowerCase()
                        .contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SocialHandleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SocialHandleContainsKeywordsPredicate) other).keywords)); // state check
    }

}

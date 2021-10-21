package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;

/**
 * Represents a Statistic in the address book.
 * Guarantees: immutable
 */
public class Statistic {

    public final List<Person> list;

    /**
     * Constructs a {@code Statistic}.
     *
     * @param list A valid filtered persons list.
     */
    public Statistic(List<Person> list) {
        requireNonNull(list);
        this.list = list;
    }

    GenderStatistic computeGenderStatistic() {
        int numberOfMales = 0;
        int numberOfFemales = 0;
        int numberOfOthers = 0;

        for (Person p: list) {
            Gender gender = p.getGender();

            if (gender.isMale()) {
                numberOfMales++;
            } else if (gender.isFemale()) {
                numberOfFemales++;
            } else {
                numberOfOthers++;
            }
        }

        return new GenderStatistic(numberOfMales, numberOfFemales, numberOfOthers);
    }

    NationalityStatistic computeNationalityStatistic() {
        HashMap<String, Integer> nationalitiesCount = new HashMap<String, Integer>();

        for (Person p: list) {
            String nationality = p.getNationality().toString();
            int currCount = nationalitiesCount.getOrDefault(nationality, 0);

            nationalitiesCount.put(nationality, currCount + 1);
        }

        return new NationalityStatistic(nationalitiesCount);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Statistic)) { //this handles null as well.
            return false;
        }

        Statistic otherStats = (Statistic) other;

        return this.list.equals(otherStats.list);
    }

    /**
     * Format statistics as text for viewing.
     */
    public String toString() {
        return String.format("%s\n\n%s", computeGenderStatistic(), computeNationalityStatistic());
    }

}

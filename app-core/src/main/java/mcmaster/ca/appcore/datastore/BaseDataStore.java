package mcmaster.ca.appcore.datastore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BaseDataStore {
    public static final int MAX_RESULTS_FOR_EXPERT = 5;
    public static final String RESULTS_PARAM = "PeopleResultsPARAM";
    private final ArrayList<PersonResult> results = new ArrayList<>();

    public BaseDataStore() {

    }

    /**
     * Retrieve a list of people results in the correct order.
     *
     * @return List of people results.
     */
    public ArrayList<PersonResult> getFinalResults() {
        Collections.sort(results);
        return results;
    }

    /**
     * Called when a new set of people results are received.
     *
     * @param personResults
     *     The new list of people results.
     */
    public void onReceivedPeopleResults(List<PersonResult> personResults) {
        for (PersonResult person : personResults) {
            // If the person is already in the list, increase the score.
            if (results.contains(person)) {
                results.get(results.indexOf(person)).increaseScore(person.score);
            } else {
                // If not in the list, just append to the end.
                results.add(person);
            }
        }
    }
}

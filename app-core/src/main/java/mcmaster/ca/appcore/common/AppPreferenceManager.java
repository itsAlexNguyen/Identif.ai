package mcmaster.ca.appcore.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.content.Context.MODE_PRIVATE;
import mcmaster.ca.appcore.datastore.PersonResult;
import mcmaster.ca.appcore.network.models.AppSearchResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppPreferenceManager {
    private static final String USER_DATA_KEY = "UserData";
    private static final String RECENT_SEARCHES_KEY = "RECENT_SEARCHES";
    private static final String SAVED_SEARCHES_KEY = "SAVED_SEARCHES";

    // Context to obtain app data.
    private final Context context;

    public AppPreferenceManager(Context context) {
        this.context = context;
    }

    /**
     * Saves a new search result.
     *
     * @param newResults
     *     The search result to save.
     */
    public void saveSearchResult(AppSearchResult newResults) {
        SharedPreferences userData = context.getSharedPreferences(USER_DATA_KEY, MODE_PRIVATE);
        String savedSearches = userData.getString(SAVED_SEARCHES_KEY, null);
        if (savedSearches == null) {
            List<AppSearchResult> newList = new ArrayList<>();
            newList.add(newResults);
            Gson gson = new Gson();
            String json = gson.toJson(newList);
            SharedPreferences.Editor edit = userData.edit();
            edit.putString(SAVED_SEARCHES_KEY, json);
            edit.apply();
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<List<AppSearchResult>>() {}.getType();
            List<AppSearchResult> newList = gson.fromJson(savedSearches, type);
            newList.add(newResults);
            String json = gson.toJson(newList);
            SharedPreferences.Editor edit = userData.edit();
            edit.putString(SAVED_SEARCHES_KEY, json);
            edit.apply();
        }
    }

    /**
     * Saves a new recent search.
     *
     * @param newResults
     *     The search result to save.
     */
    public void saveRecentSearchResult(AppSearchResult newResults) {
        SharedPreferences userData = context.getSharedPreferences(USER_DATA_KEY, MODE_PRIVATE);
        String savedSearches = userData.getString(RECENT_SEARCHES_KEY, null);

        if (savedSearches == null) {
            List<AppSearchResult> newList = new ArrayList<>();
            newList.add(newResults);
            Gson gson = new Gson();
            String json = gson.toJson(newList);
            SharedPreferences.Editor edit = userData.edit();
            edit.putString(RECENT_SEARCHES_KEY, json);
            edit.apply();
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<List<AppSearchResult>>() {}.getType();
            List<AppSearchResult> newList = gson.fromJson(savedSearches, type);
            newList.add(newResults);
            String json = gson.toJson(newList);
            SharedPreferences.Editor edit = userData.edit();
            edit.putString(RECENT_SEARCHES_KEY, json);
            edit.apply();
        }
    }

}

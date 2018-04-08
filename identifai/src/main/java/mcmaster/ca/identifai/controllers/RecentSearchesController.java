package mcmaster.ca.identifai.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mcmaster.ca.appcore.common.AppPreferenceManager;
import mcmaster.ca.appcore.network.models.AppSearchResult;
import mcmaster.ca.identifai.R;

import java.util.List;

public class RecentSearchesController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_searches_controller);
        AppPreferenceManager preferenceManager = new AppPreferenceManager(this);
        List<AppSearchResult> results = preferenceManager.getRecentSearchResults();
        setTitle("Recent Searches");
    }
}
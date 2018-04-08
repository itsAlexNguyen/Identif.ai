package mcmaster.ca.identifai.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import mcmaster.ca.appcore.common.AppPreferenceManager;
import mcmaster.ca.appcore.common.InputListener;
import mcmaster.ca.appcore.network.models.AppSearchResult;
import mcmaster.ca.appcore.ui.adapters.AbstractDataBindAdapter;
import mcmaster.ca.appcore.ui.binder.SearchResultsBinder;
import mcmaster.ca.identifai.R;

import java.util.List;

public class SaveSearchesController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_searches_controller);
        setTitle(R.string.saved_searches_header);
        AppPreferenceManager preferenceManager = new AppPreferenceManager(this);
        List<AppSearchResult> results = preferenceManager.getSavedSearchResults();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SaveSearchesController.Adapter(results));
    }

    private InputListener<AppSearchResult> createResultListener() {
        return new InputListener<AppSearchResult>() {
            @Override
            public void onInputReceived(AppSearchResult value) {
                Intent intent = new Intent(SaveSearchesController.this, OutputController.class);
                intent.putParcelableArrayListExtra(OutputController.RESULTS_PARAM, value.results);
                startActivity(intent);
            }
        };
    }

    public class Adapter extends AbstractDataBindAdapter {
        private final List<AppSearchResult> results;

        public Adapter(List<AppSearchResult> results) {
            this.results = results;
            buildRows();
        }

        private void buildRows() {
            listItems.clear();
            for (AppSearchResult search : results) {
                listItems.add(new SearchResultsBinder(search, createResultListener()));
            }
        }
    }
}

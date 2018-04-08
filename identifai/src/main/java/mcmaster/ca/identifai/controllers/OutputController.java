package mcmaster.ca.identifai.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import mcmaster.ca.appcore.common.AppPreferenceManager;
import mcmaster.ca.appcore.common.InputListener;
import mcmaster.ca.appcore.datastore.PersonResult;
import mcmaster.ca.appcore.network.models.AppSearchResult;
import mcmaster.ca.appcore.ui.adapters.AbstractDataBindAdapter;
import mcmaster.ca.appcore.ui.binder.PersonDataBinder;
import mcmaster.ca.identifai.R;

import java.util.ArrayList;
import java.util.List;

public class OutputController extends AppCompatActivity {
    public static final String RESULTS_PARAM = "ResultsPARAM";
    public static final String IS_RECENT_SEARCH_PARAM = "isRecentSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_controller);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.output_title);
        }
        final ArrayList<PersonResult> resultList = getIntent().getParcelableArrayListExtra(RESULTS_PARAM);
        if (resultList != null) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Adapter(resultList));
        }
        boolean shouldBeRecentSearch = getIntent().getBooleanExtra(IS_RECENT_SEARCH_PARAM, false);
        if (shouldBeRecentSearch) {
            AppPreferenceManager preferenceManager = new AppPreferenceManager(this);
            AppSearchResult newSearch = new AppSearchResult(resultList);
            preferenceManager.saveRecentSearchResult(newSearch);
        }

        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppPreferenceManager preferenceManager = new AppPreferenceManager(OutputController.this);
                AppSearchResult newSearch = new AppSearchResult(resultList);
                preferenceManager.saveSearchResult(newSearch);
            }
        });
    }

    private InputListener<PersonResult> createSelectionListener() {
        return new InputListener<PersonResult>() {
            @Override
            public void onInputReceived(PersonResult value) {
                Intent intent = new Intent(OutputController.this, PersonDetailController.class);
                intent.putExtra(PersonDetailController.PERSON_PARAM, value);
                startActivity(intent);
            }
        };
    }

    public class Adapter extends AbstractDataBindAdapter {
        private final List<PersonResult> resultList;

        public Adapter(List<PersonResult> resultList) {
            super();
            this.resultList = resultList;
            buildRows();
        }

        private void buildRows() {
            listItems.clear();
            for (PersonResult person : resultList) {
                listItems.add(new PersonDataBinder(person, createSelectionListener()));
            }
        }
    }
}

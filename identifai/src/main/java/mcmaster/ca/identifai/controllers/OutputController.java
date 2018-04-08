package mcmaster.ca.identifai.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import mcmaster.ca.appcore.datastore.PersonResult;
import mcmaster.ca.appcore.ui.adapters.AbstractDataBindAdapter;
import mcmaster.ca.appcore.ui.binder.PersonDataBinder;
import mcmaster.ca.identifai.R;

import java.util.List;

public class OutputController extends AppCompatActivity {
    public static final String RESULTS_PARAM = "ResultsPARAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_controller);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.output_title);
        }
        List<PersonResult> resultList = getIntent().getParcelableArrayListExtra(RESULTS_PARAM);
        if (resultList != null) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Adapter(resultList));
        }
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
                listItems.add(new PersonDataBinder(person));
            }
        }
    }
}

package mcmaster.ca.text;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import mcmaster.ca.appcore.datastore.BaseDataStore;
import static mcmaster.ca.appcore.datastore.BaseDataStore.RESULTS_PARAM;
import mcmaster.ca.appcore.datastore.PersonResult;
import mcmaster.ca.appcore.network.HttpCallback;
import mcmaster.ca.appcore.ui.BaseActivity;
import mcmaster.ca.text.models.CastMember;
import okhttp3.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextEntryController extends BaseActivity {
    public static final int RESULT_CODE = 2001;
    private final TextDetectionService service = new TextDetectionService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_entry_controller);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        final EditText textEntry = findViewById(R.id.text_entry_edittext);
        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textEntry.getText() != null && !textEntry.getText().toString().isEmpty()) {
                    submitQuery(textEntry.getText().toString());
                }
            }
        });

        textEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textEntry.getText() != null && !textEntry.getText().toString().isEmpty()) {
                    submitQuery(textEntry.getText().toString());
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void submitQuery(String text) {
        showLoading();
        service.retrieveActors(text, new HttpCallback<List<CastMember>>() {
            @Override
            public void onSuccess(Call call, List<CastMember> response) {
                hideLoading();
                handleNetworkResponse(response);
            }

            @Override
            public void onFailure(Call call, IOException exception) {
                hideLoading();
            }
        });
    }

    private void handleNetworkResponse(List<CastMember> response) {
        ArrayList<PersonResult> convertedResults = new ArrayList<>();
        if (response != null && !response.isEmpty()) {
            for (int i = 0; i < Math.min(response.size(), BaseDataStore.MAX_RESULTS_FOR_EXPERT); i++) {
                CastMember member = response.get(i);
                convertedResults.add(new PersonResult(member.name, 5 - i));
            }
        }
        Intent data = new Intent();
        data.putParcelableArrayListExtra(RESULTS_PARAM, convertedResults);
        setResult(RESULT_CODE, data);
        finish();
    }
}

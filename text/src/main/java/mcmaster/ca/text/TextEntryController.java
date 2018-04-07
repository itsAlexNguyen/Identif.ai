package mcmaster.ca.text;

import android.os.Bundle;

import mcmaster.ca.appcore.ui.BaseActivity;

public class TextEntryController extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_entry_controller);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}

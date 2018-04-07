package mcmaster.ca.identifai.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mcmaster.ca.appcore.datastore.BaseDataStore;
import mcmaster.ca.appcore.datastore.PersonResult;
import mcmaster.ca.appcore.ui.BaseActivity;
import mcmaster.ca.identifai.R;
import mcmaster.ca.sound.SoundController;
import mcmaster.ca.text.TextEntryController;
import mcmaster.mcmaster.ca.image.ImageEntryController;

import java.util.List;

public class InputController extends BaseActivity {
    private static final int RESULTS_REQUEST_CODE = 1001;
    private final BaseDataStore dataStore = new BaseDataStore();
    Button soundButton;
    Button imageButton;
    Button textButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_controller);
        setTitle(R.string.input_data);

        soundButton = findViewById(R.id.sound_buuton);
        imageButton = findViewById(R.id.image_button);
        textButton = findViewById(R.id.text_button);

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(SoundController.class, RESULTS_REQUEST_CODE);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(ImageEntryController.class, RESULTS_REQUEST_CODE);
            }
        });
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(TextEntryController.class, RESULTS_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // If not data passed back, we should just ignore.
        if (data == null) {
            return;
        }
        if (requestCode == RESULTS_REQUEST_CODE) {
            List<PersonResult> resultList = data.getParcelableArrayListExtra(BaseDataStore.RESULTS_PARAM);
            dataStore.onReceivedPeopleResults(resultList);
        }

        if (resultCode == TextEntryController.RESULT_CODE) {
            textButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            textButton.setTextColor(Color.WHITE);
            textButton.setEnabled(false);
        }

        if (resultCode == ImageEntryController.RESULT_CODE) {
            imageButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            imageButton.setTextColor(Color.WHITE);
            imageButton.setEnabled(false);
        }

        if (resultCode == SoundController.RESULT_CODE) {
            soundButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            soundButton.setTextColor(Color.WHITE);
            soundButton.setEnabled(false);
        }
    }
}

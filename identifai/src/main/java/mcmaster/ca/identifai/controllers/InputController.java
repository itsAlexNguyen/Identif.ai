package mcmaster.ca.identifai.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mcmaster.ca.appcore.ui.BaseActivity;
import mcmaster.ca.identifai.R;
import mcmaster.ca.sound.SoundController;
import mcmaster.ca.text.TextEntryController;
import mcmaster.mcmaster.ca.image.ImageEntryController;

public class InputController extends BaseActivity {
    private static final int RESULTS_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_controller);
        setTitle(R.string.input_data);

        Button soundButton = findViewById(R.id.sound_buuton);
        Button imageButton = findViewById(R.id.image_button);
        Button textButton = findViewById(R.id.text_button);

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
        if (requestCode == RESULTS_REQUEST_CODE) {

        }
    }
}

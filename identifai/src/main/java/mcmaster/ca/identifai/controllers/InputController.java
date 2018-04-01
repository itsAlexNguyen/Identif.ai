package mcmaster.ca.identifai.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mcmaster.ca.identifai.R;
import mcmaster.ca.sound.SoundController;
import mcmaster.ca.text.TextEntryController;
import mcmaster.mcmaster.ca.image.ImageEntryController;

public class InputController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_controller);

        Button soundButton = findViewById(R.id.sound_buuton);
        Button imageButton = findViewById(R.id.image_button);
        Button textButton = findViewById(R.id.text_button);

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputController.this, SoundController.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputController.this, ImageEntryController.class);
                startActivity(intent);
            }
        });
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputController.this, TextEntryController.class);
                startActivity(intent);
            }
        });
    }
}

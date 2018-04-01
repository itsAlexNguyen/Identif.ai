package mcmaster.mcmaster.ca.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.IpCons;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;

import mcmaster.ca.image.R;

import java.io.File;
import java.util.List;

public class ImageEntryController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_entry_controller);
        Button imageEntryButton = findViewById(R.id.select_image_button);
        imageEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSelectImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            List<Image> images = ImagePicker.getImages(data);
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
            findViewById(R.id.select_image_button).setVisibility(View.GONE);

            File imgFile = new File(image.getPath());

            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ImageView imageView = findViewById(R.id.image_view);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(myBitmap);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openSelectImage() {
        startActivityForResult(ImagePicker.create(this)
            .single()
            .returnMode(ReturnMode.ALL)
            .getIntent(this), IpCons.RC_IMAGE_PICKER);
    }
}

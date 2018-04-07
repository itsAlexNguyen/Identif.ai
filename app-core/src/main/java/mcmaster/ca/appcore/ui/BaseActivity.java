package mcmaster.ca.appcore.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mcmaster.ca.appcore.R;

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.app_loading));
    }

    /**
     * Displays a generic progress dialog over the Activity.
     */
    protected void showLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.show();
            }
        });
    }

    /**
     * Hides the progress dialog is there is one running.
     */
    protected void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * Easy way to start activity.
     *
     * @param activityClass
     *     The activity to start.
     */
    protected void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    /**
     * Easy way to start an activity to start for returning a result.
     *
     * @param activityClass
     *     The activity to start.
     * @param requestCode
     *     The request code.
     */
    protected void startActivityForResult(Class<?> activityClass, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        startActivityForResult(intent, requestCode);
    }
}

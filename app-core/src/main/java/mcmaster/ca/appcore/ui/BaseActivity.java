package mcmaster.ca.appcore.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

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

package ee.help.helpee.activities;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ian on 25/04/15.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
        super.onCreate(savedInstanceState);
    }
}

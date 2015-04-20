package ee.help.helpee;

import com.facebook.FacebookSdk;

import android.app.Application;

/**
 * Created by ian on 19/04/15.
 */
public class HelpeeApplication extends Application {

    private static HelpeeApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        FacebookSdk.sdkInitialize(this);
    }

    public static HelpeeApplication getInstance() {
        return instance;
    }

}

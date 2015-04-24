package ee.help.helpee;

import com.facebook.FacebookSdk;

import android.app.Application;

import ee.help.helpee.models.User;

/**
 * Created by ian on 19/04/15.
 */
public class HelpeeApplication extends Application {

    private static HelpeeApplication instance;
    public static User userInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        FacebookSdk.sdkInitialize(this);
    }

    public static HelpeeApplication getInstance() {
        return instance;
    }


    public static User getUserInstance() {
        return userInstance;
    }

    public static void setUserInstance(User userInstance) {
        HelpeeApplication.userInstance = userInstance;
    }
}

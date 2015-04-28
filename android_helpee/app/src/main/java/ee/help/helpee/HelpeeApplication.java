package ee.help.helpee;

import com.google.gson.Gson;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseInstallation;

import android.app.Application;
import android.content.Context;

import ee.help.helpee.custom.Constants;
import ee.help.helpee.models.User;

/**
 * Created by ian on 19/04/15.
 */
public class HelpeeApplication extends Application {

    private static HelpeeApplication instance;

    public static User userInstance;

    public static String userCity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FacebookSdk.sdkInitialize(this);
        Parse.initialize(this, "ErveS8KjqjEL6BNhCxXByAeKJOvQoawLdJQrLK9n", "9soIopAA2W7lhp0zfoTWwplrvUyasRshTCfnVIXB");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    public static HelpeeApplication getInstance() {
        return instance;
    }


    public static User getUserInstance() {
        if (userInstance == null) {
            String userAsJson;
            userAsJson = getInstance().getSharedPreferences(Constants.HELPEE_PREFS, Context.MODE_PRIVATE)
                    .getString(Constants.USER_ITEM, null);
            if (userAsJson != null) {
                userInstance = new Gson().fromJson(userAsJson, User.class);
            }

        }
        return userInstance;
    }



    public static String getUserCity() {
        return userCity;
    }

    public static void setUserCity(String userCity) {
        HelpeeApplication.userCity = userCity;
    }

    public static void setUserInstance(User userInstance) {
        HelpeeApplication.userInstance = userInstance;
    }
}

package ee.help.helpee;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import net.danlew.android.joda.JodaTimeAndroid;

import ee.help.helpee.custom.Constants;
import ee.help.helpee.models.User;

/**
 * Created by ian on 19/04/15.
 */
public class HelpeeApplication extends Application {

    private static HelpeeApplication instance;

    private static User userInstance;

    private static String userCity;

    private static LatLng lastKnownLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        JodaTimeAndroid.init(this);
        FacebookSdk.sdkInitialize(this);
        Parse.initialize(this, "ErveS8KjqjEL6BNhCxXByAeKJOvQoawLdJQrLK9n", "9soIopAA2W7lhp0zfoTWwplrvUyasRshTCfnVIXB");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("deviceid");
        ParsePush.subscribeInBackground("a".concat(ParseInstallation.getCurrentInstallation().getInstallationId()));
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

    public static void changePoints(int points){
        userInstance.setPoints(points);
        String userAsJson = new Gson().toJson(userInstance);
        HelpeeApplication.getInstance().getSharedPreferences(Constants.HELPEE_PREFS, Context.MODE_PRIVATE).edit()
                .putString(Constants.USER_ITEM, userAsJson).commit();

        HelpeeApplication.userInstance = userInstance;

    }
    public static LatLng getLastKnownLocation() {
        return lastKnownLocation;
    }

    public static void setLastKnownLocation(LatLng lastKnownLocation) {
        HelpeeApplication.lastKnownLocation = lastKnownLocation;
    }

    public static String getUserCity() {
        return userCity;
    }

    public static void setUserCity(String userCity) {
        HelpeeApplication.userCity = userCity;
    }

    public static void setUserInstance(User userInstance) {
        String userAsJson = new Gson().toJson(userInstance);
        HelpeeApplication.getInstance().getSharedPreferences(Constants.HELPEE_PREFS, Context.MODE_PRIVATE).edit()
                .putString(Constants.USER_ITEM, userAsJson).commit();

        HelpeeApplication.userInstance = userInstance;
    }
}

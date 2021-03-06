package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;

/**
 * Created by ian on 19/04/15.
 */
public interface LoginInteractor {



    void receiveUserInfo(String token, String deviceId, BaseListener<User> userListener);

    void saveUserToPreferences(User user);

}

package ee.help.helpee.mvp.presenters;

import ee.help.helpee.models.User;

/**
 * Created by ian on 19/04/15.
 */


public interface LoginPresenter {


    void loginUserWithFacebookToken(String token,String deviceId);

    void saveUser(User user);

}

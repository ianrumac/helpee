package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;

/**
 * Created by infinum on 28/04/15.
 */
public interface UserLoginInteractor {


    void loginUser(String username, String password, BaseListener<User> userBaseListener);
}

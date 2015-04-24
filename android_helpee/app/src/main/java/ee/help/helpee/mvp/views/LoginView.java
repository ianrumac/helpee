package ee.help.helpee.mvp.views;

import ee.help.helpee.models.User;

/**
 * Created by ian on 19/04/15.
 */
public interface LoginView extends BaseView {

    void userLoggedIn(User user);
}

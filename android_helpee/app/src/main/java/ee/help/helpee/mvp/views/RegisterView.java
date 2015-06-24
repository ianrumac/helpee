package ee.help.helpee.mvp.views;

import ee.help.helpee.models.User;

/**
 * Created by ian on 25/04/15.
 */
public interface RegisterView extends BaseView{

    void userRegistered(User user);
}

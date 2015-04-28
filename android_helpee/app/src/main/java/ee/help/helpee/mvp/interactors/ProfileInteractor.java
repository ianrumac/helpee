package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;

/**
 * Created by infinum on 28/04/15.
 */
public interface ProfileInteractor {


    void fetchUserProfileData(String token, BaseListener<User> userBaseListener);
}

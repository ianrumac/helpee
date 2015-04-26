package ee.help.helpee.mvp.interactors.impl;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.RegisterInteractor;
import retrofit.mime.TypedFile;

/**
 * Created by ian on 25/04/15.
 */
public class RegisterInteractorImpl implements RegisterInteractor {

    @Override
    public void sendUserData(String name, String email, String password, SimpleBaseListener stringCallback) {

    }

    @Override
    public void uploadUserPicture(TypedFile typedFile, BaseListener<User> uploadCallback) {

    }
}

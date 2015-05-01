package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.SimpleUser;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.RegisterInteractor;
import ee.help.helpee.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by ian on 25/04/15.
 */
public class RegisterInteractorImpl implements RegisterInteractor {

    ApiManager apiManager;

    @Inject
    public RegisterInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void sendUserData(String name, String email, String password, final BaseListener<User> registeredListener) {

        apiManager.getApiService().registerUster(new SimpleUser(email, password, password, name), new Callback<User>() {
            @Override
            public void success(User user, Response response) {

                registeredListener.onSuccess(user);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    public void uploadUserPicture(TypedFile typedFile, BaseListener<User> uploadCallback) {

    }
}

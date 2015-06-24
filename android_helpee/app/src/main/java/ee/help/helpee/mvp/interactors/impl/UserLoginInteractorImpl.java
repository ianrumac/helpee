package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.UserLoginInteractor;
import ee.help.helpee.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ian on 26/04/15.
 */
public class UserLoginInteractorImpl implements UserLoginInteractor {

    ApiManager apiManager;

    @Inject
    public UserLoginInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void loginUser(String username, String password, final BaseListener<User> userBaseListener) {
        apiManager.getApiService().userSignIn(username, password, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                userBaseListener.onSuccess(user);
            }

            @Override
            public void failure(RetrofitError error) {
                userBaseListener.onFail(ErrorType.CONNECTION_ERROR);
            }
        });
    }
}

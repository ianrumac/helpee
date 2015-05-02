package ee.help.helpee.mvp.interactors.impl;

import com.google.gson.Gson;

import android.content.Context;

import javax.inject.Inject;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.LoginInteractor;
import ee.help.helpee.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ian on 19/04/15.
 */
public class LoginInteractorImpl implements LoginInteractor {

    ApiManager apiManager;

    @Inject
    public LoginInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void receiveUserInfo(String accessToken, String deviceId,  final BaseListener<User> userListener) {
        apiManager.getApiService().postAccountInfo(accessToken, deviceId, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                if (user != null) {
                    userListener.onSuccess(user);
                }else
                    userListener.onFail(ErrorType.NULL_RESPONSE);
            }

            @Override
            public void failure(RetrofitError error) {
                userListener.onFail(ErrorType.CONNECTION_ERROR);
            }
        });
    }

    @Override
    public void saveUserToPreferences(User user) {
                HelpeeApplication.setUserInstance(user);
            }


}


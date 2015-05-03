package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.SimpleUser;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.RegisterInteractor;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.models.BearerToken;
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

        apiManager.getApiService().registerUser(new SimpleUser(email, password, password, name), new Callback<User>() {
            @Override
            public void success(User user, Response response) {

                registeredListener.onSuccess(user);
            }

            @Override
            public void failure(RetrofitError error) {

                if(error.getResponse().getStatus()==400){
                    registeredListener.onFail(ErrorType.AUTH_ERROR);
                }else
                    registeredListener.onFail(ErrorType.CONNECTION_ERROR);

            }
        });

    }

    @Override
    public void uploadUserPicture(final User user, String userId, TypedFile typedFile, final BaseListener<User> uploadCallback) {
            apiManager.getApiService().uploadUserImage(typedFile, userId, BearerToken.authorize(user.getToken()), new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    user.setImageUri(s);
                    uploadCallback.onSuccess(user);

                }

                @Override
                public void failure(RetrofitError error) {
            uploadCallback.onFail(ErrorType.CONNECTION_ERROR);
                }
            });
    }
}
